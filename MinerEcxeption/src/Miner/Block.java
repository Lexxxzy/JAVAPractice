package Miner;


import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data; //Просто рандомная дата для составление ХэшБлока
    private long timeStamp; //в миллисекундах от 1/1/1970.
    private int nonce;

    //Конструктор для построения блока криптовалюты
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash();
    }

    //Хэшируем наш блок
    public String calculateHash() {
        return StringHashing.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
    }

    //Увеличивает переменную nonce каждый раз пока не найдет правильный хеш
    public void mineBlock(int difficulty) {
        String target = StringHashing.getDificultyString(difficulty); //Создает строку с хешем с заданной сложностью
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce++; // Переменная котороя увеличивается на единицу, дописывается к блоку
            hash = calculateHash();
        }
        System.out.println("Замайнили блок!!! : " + hash);
    }

    public void print()
    {
        System.out.println(this.hash);
    }
}
