package Miner;

import java.util.ArrayList;

public class Main extends Thread{

    public static int difficulty = 5; //сложность для майнинга крипты(чем больше число тем больше времени потребуется на решение)


    public static void main(String[] args)
    {
        Mining m = new Mining();
        m.start();
    }
}
