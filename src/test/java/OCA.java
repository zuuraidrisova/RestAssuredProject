import java.util.Arrays;
import java.util.LinkedHashSet;

public class OCA {


    public static void main(String[] args) {

        String name = reverse("Zuura");

        System.out.println("name = " + name);

        String noDup = removeDuplicates("Zuuura");

        System.out.println("noDup = " + noDup);

        String noDup2 = removeDuplicates2("AAAABBBBBDDDDC");

        System.out.println("noDup2 = " + noDup2);

        System.out.println("removeDuplicates = " + removeDuplicates3("AAAABBBBBDDDDC"));

        int [] arr1 = {1,2,3,4,5};
        int [] arr2 = {6,7,8,9,0};

       int combined [] =  concate2Arrays(arr1,arr2);

        System.out.println("combined = " + Arrays.toString(combined));


        devided(11,2);

        finra(50);
    }
   // method which reverse  a string and returns a string

    public static String reverse(String str){

        String reversedString = "";

        for(int i = str.length()-1; i >= 0; i--){

            reversedString += str.charAt(i);
        }

        return reversedString;
    }

    public static String removeDuplicates(String str){

        String result = "";

        for(int i = 0; i < str.length(); i++){

            int count = 0;

            for(int j = 0; j < str.length(); j++){

                if(str.charAt(i) == str.charAt(j)){

                    count++;
                }
            }

            if(count == 1){

                result += str.charAt(i);

            }else{

                continue;
            }
        }

        return result;
    }

    public static String removeDuplicates2(String str){

        String result = "";

        for(int i = 0; i < str.length(); i++){

            if(!result.contains(""+str.charAt(i))){

                 result += str.charAt(i);
            }
        }

        return result;
    }

    public static String removeDuplicates3(String str){

        str = new LinkedHashSet<String>(Arrays.asList(str.split(""))).toString();

        str = str.replace(",","").replace("[","").replace("]", "");

        return str;
    }

    public static int [] concate2Arrays(int [] arr1, int [] arr2){

        int [] combined = new int [arr1.length + arr2.length];

        int k = 0;

        for(int i = 0; i < arr1.length; i++){

            combined [k] = arr1[i];

            k++;
        }

        for (int j = 0; j < arr2.length; j++){

            combined[k] = arr2[j];

            k++;
        }

        return combined;

    }

    public static void devided(int num1, int num2){

        if(num2 == 0){

            System.out.println("Not valid");

        }

        System.out.println(num1 +" is divided by "+num2 +" is ");

        int count = 0;
        while (num1 >= num2){

            num1 -= num2;

            count++;
        }

        System.out.println(count +" and remainder is "+num1);
    }


    public static void finra(int num) {

        for (int i=1; i<=num; i++) {

            String str = "";

            if (i % 3 == 0) str = "FIN";

            if (i % 5 == 0) str += "RA";

            System.out.println(str.isEmpty() ? i : str);

        }
    }

}
