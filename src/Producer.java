import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

public class Producer implements Runnable {
    //Order
    private CircularQueue<Integer> buffer;
    private int delay;
    private Integer id;


    public Producer(Integer id, int delay, CircularQueue<Integer> buffer) {
        this.delay = delay;
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //String getRandomUUID = UUID.randomUUID().toString().substring(0, 5);

                System.out.printf("Generated data by thread %s: [%s]\n", this.id, getRandomInteger());
                buffer.setElement(getRandomInteger());
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }
    }

//    private static String getRandomString() { //for tests
//        byte [] array = new byte[7];
//        new Random().nextBytes(array);
//
//        return new String(array, Charset.defaultCharset());
//    }

    private static Integer getRandomInteger() throws InterruptedException {
        return new Random().nextInt(10);
    }

//    public static void main(String[] args) { //for tests
//        System.out.println(getRandomString());
//    }
}
