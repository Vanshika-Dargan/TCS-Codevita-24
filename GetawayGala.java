
import java.util.*;
public class GetawayGala
    {

        static boolean isPalindrome(String str){
        
            int i=0;
            int j=str.length()-1;
            while(i<j){
                if(str.charAt(i)!=str.charAt(j))
                return false;
                i++;
                j--;
            }
        return true;
        }

        public static String luckyDraw(String employees[],int N){
            
            int len=employees.length;
            List<String> list = new ArrayList<String>();
            int last[]=new int[26];
            Arrays.fill(last,-1);
            //round one
            String str="";
            int c=0;
            for(int i=0;i<len;i++){
                char ch=employees[i].charAt(0);
                ch=Character.toLowerCase(ch);

                if(last[ch-'a']==-1){
                    str+=ch;
                    list.add(employees[i]);
                    last[ch-'a']=c++;
                }
                else{
                    String temp=str.substring(last[ch-'a'])+ch;
                   
                    if(!isPalindrome(temp)){
                        str+=ch;
                    list.add(employees[i]);
                    last[ch-'a']=c++;
                    }
                }
            }
            System.out.println(list);
            //round two
            int idx=0;
            while(list.size()>1){
                idx=(idx+N-1)%list.size(); 
                list.remove(idx);
            }
            return list.get(0);

        }
        public static void main(String[] args) {
           Scanner sc=new Scanner(System.in);
        //    String str=sc.nextLine();
        //    String employees[]=str.split(" ");
        //    int N=sc.nextInt();
        String str="Janu gita sana gopi jaslin Tony Ritu Naina sonu Neha";
        String employees[]=str.split(" ");
        int N=2;
           String res=luckyDraw(employees,N);
           System.out.print(res);
           sc.close();
        }
}