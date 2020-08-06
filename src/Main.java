import java.util.Arrays;

public class Main {
    static final int Size=1_000_000;//10000000;
    static final int Half=Size/2;

    public static void main(String[] args) {
        create_arr();
       // create_arr1();


        //float[] arr=create_arr();
       // for(float tmp:arr){
        //    System.out.println(tmp);
        //}

    }

    public static void create_arr(){
        float[] arr=new float[Size];
        Arrays.fill(arr,1.0f);
        long a = System.currentTimeMillis();

        for (int i=0;i<arr.length;i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis()-a);
    }

    public static void create_arr1(){
        float[] arr=new float[Size];
        Arrays.fill(arr,1.0f);
        long a = System.currentTimeMillis();
        float[] arr1=new float[Half];
        float[] arr2=new float[Half];
        System.arraycopy(arr,0,arr1,0,Half);
        System.arraycopy(arr,Half,arr2,0,Half);

        Thread thread_arr1=new Thread(() -> {
            for (int i=0;i<arr1.length;i++){
                arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread_arr2=new Thread(() -> {
            for (int i=0,j=Half;i<arr2.length;i++,j++){
                arr2[i] = (float)(arr2[i] * Math.sin(0.2f + (j) / 5) * Math.cos(0.2f + (j) / 5) * Math.cos(0.4f + (j) / 2));
            }
        });

        thread_arr1.start();
        thread_arr2.start();
        try {
            thread_arr1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread_arr2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1,0,arr,0,Half);
        System.arraycopy(arr2,0,arr,Half,Half);

        System.out.println(System.currentTimeMillis()-a);
    }

}
