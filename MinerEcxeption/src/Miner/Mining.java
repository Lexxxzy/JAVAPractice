package Miner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static Miner.Main.difficulty;

public class Mining extends Thread{
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    @Override
    public void run() {
        try
        {
            for (int i = 1; i < 7; i++) { //майним 7 блоков
                if (i==1)
                {
                    System.out.println("Майним 1 блок... ");
                    addBlock(new Block("Hi im the first block", "0"));
                }
                System.out.println("Майним " + i + " блок");
                addBlock(new Block("Блок под номером " + Integer.toString(i),blockchain.get(blockchain.size()-1).hash));
            }
            Thread.sleep(1000); // после каждого майна процесс засыпает
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        Block[] blocksArray = new Block[10];
        int i = 0;
        //добавление в обычный массив всей последовательности (ArrayIndexOutOfBoundsException)
        for (Block b: blockchain) {
            try {
                blocksArray[i] = b;
                i++;
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println(e);
            }
        }


        System.out.println("\nВерен ли Blockchain: " + isChainValid());

        if(isChainValid())//IOExeption - пишем в файл всю последовательность если блокчейн верен
        {
            try {
                FileOutputStream dest = new FileOutputStream("C:/Users/Lex/IdeaProjects/MinerEcxeption/destination.txt");
                for (Block b : blocksArray) {
                    try {
                        dest.write(b.hash.getBytes());
                    }catch (NullPointerException e)
                    {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nThe block chain: " + " \n");

            try {
                printAllHashes(blocksArray);
            } catch (Exception e)
            {
                System.out.println("\u001b[38;5;196m Выход за пределы массива  \u001b[38;5;0m"+e.getMessage());
            }

            System.out.println();
        }

    }

    //NullPointerException
    public void printAllHashes(Block[] blocks) throws NullPointerException
    {
        for (int i = 0; i < blocks.length; i++) {

            System.out.println("Хэш "+i+" блока: " + blocks[i].hash);
        }
    }
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //луп через весь блокчейн чтобы проверить все хеши
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //сравнием
            if (currentBlock.hash.equals(currentBlock.calculateHash())) {//сравнием прошлый хеш и запомненный прошлый хеш
                if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                    System.out.println("Previous Hashes not equal");
                    return false;
                }
                //проверяем решен ли хеш
                if (currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                    continue;
                }
                System.out.println("Этот блок не был замайнен!");
                return false;
            } else {
                System.out.println("Хеши не совпадают");
                return false;
            }

        }
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}
