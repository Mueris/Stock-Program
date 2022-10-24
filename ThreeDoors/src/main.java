import java.util.Random;

public class main {
		
	
	public static void main(String[] args) {
		
		Random random=new Random();
		int win=0;
		int loose=0;
		int count=0;
		while(count<100000) {
			count++;
			int[] arr= new int[] {1,2,3};
			int money = random.nextInt(1,4);
			int choice= random.nextInt(1,4);
			for(int i=0;i<arr.length;i++) {
				if(arr[i]== money || arr[i]== choice) {
					arr[i]=0;
				}
			}
			int k= random.nextInt(0,3);
			while(true) {
			 k= random.nextInt(0,3);
			 if(arr[k]!=0)
				 break;
			}
			int deleted=arr[k];
			for(int i=1;i<4;i++) {
				arr[i-1]=0;
				if(deleted!=i)
					arr[i-1]=i;
			}
			for(int i=0;i<3;i++) {
				if(arr[i]!= choice&& arr[i]!=0) {
					choice=arr[i];
					break;
				}
					
				
			}
			if(choice==money) 
				win++;
			else
				loose++;
		}
		System.out.println("wins: "+win+"  looses:  "+loose);
		
		
		}
		

	

}

