package practicaltest01var08.eim.systems.cs.pub.ro.practicaltest01var08;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

/**
 * Created by student on 02.04.2018.
 */

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    StringBuilder to_be_sent;

    public ProcessingThread(Context context, int len, String answer) {
        this.context = context;

        to_be_sent = new StringBuilder(len);
        int random_position = random.nextInt(len);
        if(len != 0 ) {
        for(int i = 0;i < len;i++) {
            if(i != random_position) {
                to_be_sent.setCharAt(i, '*');
            }
            else {
                to_be_sent.setCharAt(i, answer.charAt(random_position));
            }
        }}
        Log.d("VALUE", "Value: " + to_be_sent);

    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            Log.d("VALUE", "Value: " + to_be_sent);
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[0]);
        String to_send = to_be_sent.toString();
        intent.putExtra("string", to_send);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            // la fiecare 5 sec se trimite cate un msj
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }

}
