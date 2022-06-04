package threadPool;

import main.Config;
import main.Logger;
import providers.*;
import storages.AccessoryStorage;
import storages.AutoStorage;
import storages.BodyStorage;
import storages.MotorStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThreadsPool {

    private final List<Thread> threads;

    private Thread threadSupplierProviderInit(AccessoryStorage a) {
        Supplier supplier = new Supplier(a);
        Thread thread = new Thread(supplier);
        thread.start();
        return thread;
    }

    private Thread threadMotorProviderInit(MotorStorage m){
        MotorProvider motorProvider = new MotorProvider(m);
        Thread thread = new Thread(motorProvider);
        thread.start();
        return thread;
    }

    private Thread threadBodyProviderInit(BodyStorage b){
        BodyProvider bodyProvider = new BodyProvider(b);
        Thread thread = new Thread(bodyProvider);
        thread.start();
        return thread;
    }

    private Thread threadDealerInit(int num, AutoStorage a, Logger logger) throws IOException {
        Dealer dealer = new Dealer(a, num, logger);
        Thread thread = new Thread(dealer);
        thread.start();
        return thread;
    }

    private Thread threadWorkerInit(AccessoryStorage a, BodyStorage b, MotorStorage m, AutoStorage auto){
        Worker worker = new Worker(a, b, m, auto);
        Thread thread = new Thread(worker);
        thread.start();
        return thread;
    }

    public ThreadsPool(AutoStorage auto, AccessoryStorage a, BodyStorage b, MotorStorage m, Logger logger) throws IOException {
        threads = new ArrayList<>();
        threads.add(threadBodyProviderInit(b));
        threads.add(threadMotorProviderInit(m));
        for (int i = 0; i < Config.NumSupplier; i++)
            threads.add(threadSupplierProviderInit(a));
        for (int i = 0; i < Config.NumDealers; i++)
            threads.add(threadDealerInit(i, auto, logger));
        for (int i = 0; i < Config.NumWorkers; i++)
            threads.add(threadWorkerInit(a, b, m, auto));
    }

    public void interruptThreads(){
        for (Thread thread : threads)
            thread.interrupt();
    }
}
