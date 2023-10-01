package org.example.encrypt.encrypt_directory;

import org.example.encryption_algorithm.algorithm.Encryption;
import org.example.enums.EncryptionEvents;
import org.example.runnables.EncryptionRunnable;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncDirectoryProcessor extends DirectoryProcessor {
    private ArrayList<Runnable> encryptionThreadList;

    public AsyncDirectoryProcessor(EncryptionEvents action, Encryption encryptionAlgorithm, String directoryPath) {
        super(action, encryptionAlgorithm, directoryPath);
        this.encryptionThreadList = new ArrayList<>();
    }

    private void transferFilesToList() throws Exception {
        Set<Path> filesNames = super.readDirectory();
        ArrayList<Integer> keys = super.getKeys();

        for (Path fileName : filesNames) {
            if (super.checkIfFileNeedsAction(fileName)) {
                File file = new File(super.getFileAbsolutePath(getDirectoryPath(), fileName.toString()));

                EncryptionRunnable encryptionRunnable = new EncryptionRunnable(getEncryptionAlgorithm(),
                        getArgs(file), new ArrayList<>(keys));
                this.encryptionThreadList.add(encryptionRunnable);
            }
        }
    }

    @Override
    public void startEncryption() throws Exception {
        this.transferFilesToList();

        super.notifyDirectoryEncryptionStarted();
        long timeStarted = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (Runnable runnable : this.encryptionThreadList) {
            pool.execute(runnable);
        }

        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);

        super.notifyDirectoryEncryptionEnded(timeStarted);
        this.encryptionThreadList.clear();
    }
}
