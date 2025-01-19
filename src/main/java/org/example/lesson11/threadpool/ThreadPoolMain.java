package org.example.lesson11.threadpool;

public class ThreadPoolMain {
    public static void main(String[] args) {
        /*
            A Thread Pool is an alternative to creating a new thread per task to execute.
            Instead, a number of pre-created threads exist in a pool - into which you can submit
            tasks to be executed by these pooled threads.
            New tasks submitted to the pool are first stored internally in a queue.
            From this queue the pooled threads will take the tasks and execute them.
            Each thread in the thread pool executes in a loop that takes a new task from the internal task queue in
            the thread pool (or block if no tasks are available), executes the task,
            and then tries to take another task etc.  repeatedly.
         */

        //Normalde javanin kendi thread pool u var biz burada sadece ornek yaptik
        //Blocking Queue bos ise ve threadler bekliyorsa yeni bir task queueya eklendiginde yalnizca 1 thread bunu alir ve calistirir.
        //Tasklar threadler arasinda esit bolunmeyebilir. th1 task1 i bitirene kadar th2 5 tane task yapabilir.
        //Buradaki mantik isi ilk bitiren kuyruktaki ilk taski islemeye baslar.

        int nOfThreads = 3;
        int maxNoOfTasks = 10;

        ThreadPool threadPool = new ThreadPool(nOfThreads, maxNoOfTasks);

        for (int i = 0; i < maxNoOfTasks; i++) {
            int taskNo = i;

            threadPool.execute(() -> {
                String message = Thread.currentThread().getName() + ": Task " + taskNo;
                System.out.println(message);
            });
        }

        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();


    }

}
