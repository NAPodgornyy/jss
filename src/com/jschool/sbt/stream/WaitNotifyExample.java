package com.jschool.sbt.stream;

//отправка и получение сообщений 

public class WaitNotifyExample {
    private String message;

    public void doMessages(){
        try {
            synchronized (this){
                while(message==null){
                    wait();
                }
                System.out.println("Получено сообщение: "+ message);
                message = null;
            }
        } catch (InterruptedException e){
            return;
        }
    }

    public void sendMessage(String message){
        synchronized (this){
            this.message = message;
            notify();
        }
    }

    public static void main(String[] args) {
        WaitNotifyExample example = new WaitNotifyExample();

        Thread consumer = new Thread(()->{
            while (true){

                example.doMessages();
                System.out.println("read");
            }
        });

        Thread produser = new Thread(()->{
            for (int i = 0; i<5; i++){

                example.sendMessage(String.format("send %d", i));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        consumer.start();
        produser.start();
    }
}
