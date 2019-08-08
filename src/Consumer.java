public class Consumer<T> implements Runnable {

    private CircularQueue<T> buffer;
    private Integer id;

    public Consumer(CircularQueue<T> buffer, Integer id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                T data = buffer.getElement();
                System.out.printf("Data consumed by  thread %s: [%s]\n", this.id, data);
            } catch (InterruptedException e) {}
        }
    }
}
