import java.util.*;
import java.time.LocalDate;

class ContactList{
	private Contact[] contactArray;
	private int nextindex;
	private int size;
	private int loadfactor;
	
	ContactList(int size,int loadfactor){
		contactArray=new Contact[size];
		this.size=size;
		this.loadfactor=loadfactor;
		nextindex=0;
	}
	private boolean isFull(){
		return nextindex>=contactArray.length;
	}
	private void extendArrays(){
		Contact[] temp = new Contact[contactArray.length + loadfactor];
		for (int i = 0; i < contactArray.length; i++) {
			temp[i] = contactArray[i];
		}
		contactArray = temp;
	}
	public void add(Contact contact){
		if(isFull()){
			extendArrays();
		}
		contactArray[nextindex++]=contact;
	}
	public Contact[] getTempArray(){
		Contact[] temp = new Contact[contactArray.length];
				for (int i = 0; i < contactArray.length; i++) {
			temp[i] = contactArray[i];
		}
		return temp;
	}
	
	public int SearchArray(String searchby){
		int count=0;
		for (int i=0;i<contactArray.length;i++){
			if (contactArray[i]!=null && ((contactArray[i].getName()!=null && contactArray[i].getName().equals(searchby)) || (contactArray[i].getPhone()!=null && contactArray[i].getPhone().equals(searchby)))) {
				return i;
			}
		}
		return -1;
	}
	
	public void DeleteArrayMain(int index){
		Contact[] temp=new Contact[contactArray.length-1];
		for(int i=0;i<contactArray.length;i++){
			if(i<index){
				temp[i]=contactArray[i];
			}else if(i==index){
				continue;
			}else if(i>index){
				temp[i-1]=contactArray[i];
			}
		}
		contactArray=temp;
		
		String str="";
		for (int i = 0; i < contactArray.length; i++) {
			if(i+1<9){
				contactArray[i].setId("C000"+(i+1));
			}else if(i+1<99){
				contactArray[i].setId("C00"+(i+1));
			}else if(i+1<999){
				contactArray[i].setId("C0"+(i+1));
			}else if(i+1<9999){
				contactArray[i].setId("C"+(i+1));
			}
		}
	}

	public int getsize(){
		return contactArray.length;
	}
	public int getindexsize(){
		return nextindex+1;
	}
	
}

class Contact{
	private String id;
	private String name;
	private String phone;
	private String company;
	private double salary;
	private String bday;
	
	Contact(String id,String name,String phone,String company,double salary,String bday){
		this.id=id;
		this.name=name;
		this.phone=phone;
		this.company=company;
		this.salary=salary;
		this.bday=bday;	
	}
	
	//------------ SETTERS ----------------
	
	public void setId(String id){
		this.id=id;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public void setCompany(String company){
		this.company=company;
	}
	public void setSalary(double salary){
		this.salary=salary;
	}
	public void setBday(String bday){
		this.bday=bday;
	}
	
	//------------ GETTERS ----------------
	
	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPhone(){
		return phone;
	}
	public String getCompany(){
		return company;
	}
	public double getSalary(){
		return salary;
	}
	public String getBday(){
		return bday;
	}

	
}


class contacts{
	
	public static ContactList contactlist=new ContactList(100,50);
	//----------------- VALIDATION METHODS -----------------
	
	public static boolean validday(int year,int month,int day){	//--> VALIDATE DAY METHOD.
		LocalDate currentdate = LocalDate.now();				//--> GET THE LOCAL DATE
		int currentYear=currentdate.getYear();    				//--> EXTRACTING THE YEAR VALUE FROM THE currentdate VARIABELE
		int currentMonthValue = currentdate.getMonthValue();	//--> EXTRACTING THE MONTH VALUE FROM THE currentdate VARIABELE
		int currentMonthDate=currentdate.getDayOfMonth();		//--> EXTRACTING THE DAY VALUE FROM THE currentdate VARIABELE
		
		boolean leap=true;
		if(year%400==0){ 
		    leap=true;					    
		}else if(year%4==0){ 
			if(year%100==0){ 
				leap=false;
			}else{ 
				leap=true;
			} 
		}else{ 
			leap=false; 
		}
		
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			if(day>=1 && day<=31){
				if(year==currentYear){
					if(month==currentMonthValue){
						if(day<currentMonthDate){
							return true;
						}
					}else{
						return true;
					}
				}else if(year<currentYear){
					return true;
				}
			}
			return false;	
		}else if(month==4 || month==6 || month==9 || month==11){
			if(day>=1 && day<=30){	
				if(year==currentYear){
					if(month==currentMonthValue){
						if(day<currentMonthDate){
							return true;
						}
					}else{
						return true;
					}
				}else if(year<currentYear){
					return true;
				}
			}
			return false;
		}else if(month==2){
			if(!leap){
				if(day>=1 && day<=28){
					if(year==currentYear){
						if(month==currentMonthValue){
							if(day<currentMonthDate){
								return true;
							}
						}else{
							return true;
						}
					}else if(year<currentYear){
						return true;
					}
				}
				return false;
			}else{
				if(day>=1 && day<=29){
					if(year==currentYear){
						if(month==currentMonthValue){
							if(day<currentMonthDate){
								return true;
							}
						}else{
							return true;
						}
					}else if(year<currentYear){
						return true;
					}
				}
				return false;
			}
			
		}
		return false;
	}
	public static boolean validmonth(int year,int month){		//--> VALIDATE MONTH METHOD.
		LocalDate currentdate = LocalDate.now();				//--> GET THE LOCAL DATE
		int currentYear=currentdate.getYear();    				//--> EXTRACTING THE YEAR VALUE FROM THE currentdate VARIABELE
		int currentMonthValue = currentdate.getMonthValue();	//--> EXTRACTING THE MONTH VALUE FROM THE currentdate VARIABELE
		int currentMonthDate=currentdate.getDayOfMonth();		//--> EXTRACTING THE DAY VALUE FROM THE currentdate VARIABELE
		
		if(month>=1 && month<=12){	//--> CHECK WETHER THE USER INPUTER MONTH IS BETWEEN 1 AND 12 
			if(year==currentYear){	//--> IF THE USER INPUTED YEAR IS CURRENT YEAR WE SHOULD CHECK WEATHER THE USER INPUTED MONTH LESS THAN OR EQUAL CURRENT MONTH
				if(month<=currentMonthValue){ 
					return true;	
				}
			}else if(year<currentYear){
				return true;
			}
		}
		return false;
	}
	public static boolean validyear(int year){					//--> VALIDATE YEAR METHOD.
		LocalDate currentdate = LocalDate.now();				//--> GET THE LOCAL DATE
		int currentYear=currentdate.getYear();    				//--> EXTRACTING THE YEAR VALUE FROM THE currentdate VARIABELE
		int currentMonthValue = currentdate.getMonthValue();	//--> EXTRACTING THE MONTH VALUE FROM THE currentdate VARIABELE
		int currentMonthDate=currentdate.getDayOfMonth();		//--> EXTRACTING THE DAY VALUE FROM THE currentdate VARIABELE
		
		if(year<=currentYear){
			return true;	
		}
		return false;
	}
	public static boolean validatephone(String phone){			//--> VALIDATE PHONE NUMBER METHOD.
		if(phone.length()==10){
			char str=phone.charAt(0);
			if(str=='0'){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public static boolean validatesalary(int sal){				//--> VALIDATE SALARY METHOD.
		if(sal>0){
			return true;
		}else{
			return false;
		}
	}
	public static boolean validatebday(String bday){			//--> VALIDATE DATE OF BIRTH METHOD.
		
		LocalDate currentdate = LocalDate.now();				//--> GET THE LOCAL DATE
		int currentYear=currentdate.getYear();    				//--> EXTRACTING THE YEAR VALUE FROM THE currentdate VARIABELE.
		int currentMonthValue = currentdate.getMonthValue();	//--> EXTRACTING THE MONTH VALUE FROM THE currentdate VARIABELE.
		int currentMonthDate=currentdate.getDayOfMonth();		//--> EXTRACTING THE DAY VALUE FROM THE currentdate VARIABELE.
		
		int year=Integer.parseInt(bday.substring(0,4));			//--> EXTRACTING THE YEAR VALUE FROM THE USER GIVEN INPUT.
		int month=Integer.parseInt(bday.substring(5,7));		//--> EXTRACTING THE MONTH VALUE FROM THE USER GIVEN INPUT.
		int day=Integer.parseInt(bday.substring(8));			//--> EXTRACTING THE DAY VALUE FROM THE USER GIVEN INPUT.
		
		boolean boolyear=validyear(year);			//--> GETING TRUE/FALSE BY VALIDATING THE YEAR THROUG A METHOD.
		boolean boolmonth=validmonth(year,month);	//--> GETING TRUE/FALSE BY VALIDATING THE MONTH THROUG A METHOD.
		boolean boolday=validday(year,month,day);	//--> GETING TRUE/FALSE BY VALIDATING THE DAY THROUG A METHOD.
		
		if(bday.length()==10){ //--> IF THE LENGTH IS NOT EQUAL TO 10 THEN RETURN FALSE.
			if(boolyear && boolmonth && boolday){ 
				return true;
			}
			return false;
		}else{
			return false;
		}
	}
	
	//------------------ SORT NAME METHOD ------------------

	public static void SortByName(){
		Contact[] tempsortnameArray =new Contact[contactlist.getsize()];
		for(int i=0;i<tempsortnameArray.length;i++){
			tempsortnameArray[i]=contactlist.getTempArray()[i];
		}
		for(int i=0;i<tempsortnameArray.length-1;i++){
			for(int j=0;j<tempsortnameArray.length-i-1;j++){
				if(tempsortnameArray[j].getName().charAt(0)>tempsortnameArray[j+1].getName().charAt(0)){
					Contact temp=tempsortnameArray[j];
					tempsortnameArray[j]=tempsortnameArray[j+1];
					tempsortnameArray[j+1]=temp;
				}
			}
		}
		for(int i=0;i<tempsortnameArray.length;i++){
			System.out.printf("| %-12s | %-12s | %-16s | %-16s | %-9.2f | %-12s |\n",tempsortnameArray[i].getId(),tempsortnameArray[i].getName(),tempsortnameArray[i].getPhone(),tempsortnameArray[i].getCompany(),tempsortnameArray[i].getSalary(),tempsortnameArray[i].getBday());
		}
		
	}
	
	//----------------- SORT SALARY METHOD -------------------

	public static void SortBySalary(){
		Contact[] tempsortSalartArray =new Contact[contactlist.getsize()];
		for(int i=0;i<tempsortSalartArray.length;i++){
			tempsortSalartArray[i]=contactlist.getTempArray()[i];
		}
		
		for(int i=0;i<tempsortSalartArray.length-1;i++){
			for(int j=0;j<tempsortSalartArray.length-i-1;j++){
				if(tempsortSalartArray[j].getSalary()>tempsortSalartArray[j+1].getSalary()){
					Contact temp=tempsortSalartArray[j];
					tempsortSalartArray[j]=tempsortSalartArray[j+1];
					tempsortSalartArray[j+1]=temp;
				}
			}
		}
		
		for(int i=0;i<tempsortSalartArray.length;i++){
			System.out.printf("| %-12s | %-12s | %-16s | %-16s | %-9.2f | %-12s |\n",tempsortSalartArray[i].getId(),tempsortSalartArray[i].getName(),tempsortSalartArray[i].getPhone(),tempsortSalartArray[i].getCompany(),tempsortSalartArray[i].getSalary(),tempsortSalartArray[i].getBday());
		}
	}
	
	//----------------- SORT BDAY METHOD ----------------------

	public static void SortBybday(){
		Contact[] tempsortBdaytArray =new Contact[contactlist.getsize()];
		for(int i=0;i<tempsortBdaytArray.length;i++){
			tempsortBdaytArray[i]=contactlist.getTempArray()[i];
		}
	
		for(int i=0;i<tempsortBdaytArray.length-1;i++){
			for(int j=0;j<tempsortBdaytArray.length-i-1;j++){
				int year_1=Integer.parseInt(tempsortBdaytArray[j].getBday().substring(0,4));
				int month_1=Integer.parseInt(tempsortBdaytArray[j].getBday().substring(5,7));
				int day_1=Integer.parseInt(tempsortBdaytArray[j].getBday().substring(8));
				
				int year_2=Integer.parseInt(tempsortBdaytArray[j+1].getBday().substring(0,4));
				int month_2=Integer.parseInt(tempsortBdaytArray[j+1].getBday().substring(5,7));
				int day_2=Integer.parseInt(tempsortBdaytArray[j+1].getBday().substring(8));
				
				if(year_1>year_2){
					Contact temp=tempsortBdaytArray[j];
					tempsortBdaytArray[j]=tempsortBdaytArray[j+1];
					tempsortBdaytArray[j+1]=temp;
				}else if(year_1==year_2){
					if(month_1>month_2){
						Contact temp=tempsortBdaytArray[j];
						tempsortBdaytArray[j]=tempsortBdaytArray[j+1];
						tempsortBdaytArray[j+1]=temp;
					}else if(month_1==month_2){
						if(day_1>day_2){
							Contact temp=tempsortBdaytArray[j];
							tempsortBdaytArray[j]=tempsortBdaytArray[j+1];
							tempsortBdaytArray[j+1]=temp;
						}
					}
				}
			}
		}
	}

	//------- AUTO GENARATE CONTACT ID ---------------------

	public static String contactidgenarate(){
		String str="";
		
		if(contactlist.getindexsize()-1<9){
			str="C000"+contactlist.getindexsize();
		}else if(contactlist.getindexsize()-1<99){
			str="C00"+contactlist.getindexsize();
		}else if(contactlist.getindexsize()-1<999){
			str="C0"+contactlist.getindexsize();
		}else if(contactlist.getindexsize()-1<9999){
			str="C"+contactlist.getindexsize();
		}
		return str;
		
	}

    //------------- CLEAR CONSOLE METHOD --------------------
    
    public final static void clearConsole(){
        try {   
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c","cls").inheritIO().start().waitFor();
            }else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }catch (final Exception e) {
            e.printStackTrace();
        }
    }

	//----------------- MAIN METHOD ----------------
	
	public static void main(String args[]){
		Scanner input=new Scanner(System.in);
	{
			clearConsole();
			System.out.println("\t\t          /$$ /$$$$$$$$ /$$$$$$$  /$$$$$$ /$$$$$$$$ /$$   /$$ /$$$$$$$                       ");
			System.out.println("\t\t          |__/| $$_____/| $$__  $$|_  $$_/| $$_____/| $$$ | $$| $$__  $$                    ");
			System.out.println("\t\t           /$$| $$      | $$  \\ $$  | $$  | $$      | $$$$| $$| $$  \\ $$           ");
			System.out.println("\t\t          | $$| $$$$$   | $$$$$$$/  | $$  | $$$$$   | $$ $$ $$| $$  | $$                     ");
			System.out.println("\t\t          | $$| $$__/   | $$__  $$  | $$  | $$__/   | $$  $$$$| $$  | $$                  ");
			System.out.println("\t\t          | $$| $$      | $$  \\ $$  | $$  | $$      | $$\\  $$$| $$  | $$            ");
			System.out.println("\t\t          | $$| $$      | $$  | $$ /$$$$$$| $$$$$$$$| $$ \\  $$| $$$$$$$/                    ");
			System.out.println("\t\t          |__/|__/      |__/  |__/|______/|________/|__/  \\__/|_______/                 ");
			System.out.println("\n\n");
			
			System.out.println("\t\t  _____             _             _          ____                        _");
			System.out.println("\t\t / ____|           | |           | |        / __ \\                      (_) ");
			System.out.println("\t\t| |      ___  _ __ | |_ __ _  ___| |_ ___  | |  | |_ __ __ _  __ _ _ __  _ _______ _ __     ");
			System.out.println("\t\t| |     / _ \\| '_ \\| __/ _' |/ __| __/ __| | |  | | '__/ _' |/ _' | '_ \\| |_  / _ \\ '__|");
			System.out.println("\t\t| |____| (_) | | | | || |_| | |__| |_\\__ \\ | |__| | | | |_| | |_| | | | | |/ /  __/ |");
			System.out.println("\t\t \\_____ \\___/|_| |_|\\__\\__,_|\\___|\\__|___/  \\____/|_|  \\__, |\\__,_|_| |_|_/___\\___|_|");
			System.out.println("\t\t                                                        __/ | ");
			System.out.println("\t\t                                                       |___/ ");
			System.out.println("\n");
			System.out.println("===================================================================================================================\n\n");
		
		
		//------- MAIN OPTIONS --------------
		
		System.out.println("\t[01]ADD Contacts");
		System.out.println("\t[02]UPDATE Contacts");
		System.out.println("\t[03]DELETE Contacts");
		System.out.println("\t[04]SEARCH Contacts");
		System.out.println("\t[05]LIST Contacts");
		System.out.println("\t[06]EXIT\n\n");
	}	
		System.out.print("Enter am option to continue :");
		int mainop=input.nextInt();
		
		if(mainop==1){//--------- ADD CONTACTS -------------
			char op1='y';
			while(op1=='y' || op1 =='Y'){
				clearConsole();
				//---------- HEADER ---------------
				{
					System.out.println("+---------------------------------------+ ");
					System.out.println("|\t\tADD CONTACTS\t\t| ");
					System.out.println("+---------------------------------------+ \n\n");
				}
				//---------- PRINT THE CONTACT ID AUTO GENARATED ---------------
				
					String contactid=contactidgenarate();
					System.out.println(contactid); 
					System.out.println("========\n");
				
				//-------- INPUT NAME -----------------
					
					System.out.print("Name\t\t\t:");
					String name=input.next();// ADD NAME TO THE NAMEARRAY
				
				// ------- INPUT PHONE NUMBER ---------
				
					String phone="";
					do{
						System.out.print("Phone Number\t\t:");
						phone=input.next();
						if(!(validatephone(phone))){ // VALIDATE PHONE NUMBER
							System.out.println("\tInvalid phone number....");
							System.out.print("Do you want to Enter phone number again(Y/N): ");
							char op1_1=input.next().charAt(0);
							if(op1_1=='y' || op1_1=='Y'){ // IF ENTERED Y GO ENTER OHONE NUMBER AGAIN
								System.out.print("\033[3A"); //ERASE 3 CODE LINES UPWARDS 
								System.out.print("\033[0J"); 
							}else{
								main(null);// IF ENTERED N GO TO THE MAIN PAGE
								break;
							}
						}else{
							break;// ADD NAME TO THE PHONEARRAY
						}	
					}while(!validatephone(phone));// UNTIL THE CONDITION BECOMES TRUE LO0P WILL RUN
				
				//-------- INPUT COMPANY NAME ---------
				
					System.out.print("Company Name\t\t:");
					String cname=input.next();
				
				//-------- INPUT SALARY ---------------
							
					int sal=0;
					do{
						System.out.print("Salary\t\t\t:");
						sal=input.nextInt();
						if(!(validatesalary(sal))){
							System.out.println("\tInvalid Salary....");
							System.out.print("Do you want to Enter Salary again(Y/N): ");
							char op1_2=input.next().charAt(0);
							if(op1_2=='y' || op1_2=='Y'){
								System.out.print("\033[3A"); //ERASE 3 CODE LINES UPWARDS 
								System.out.print("\033[0J");
							}else{
								main(null);
								break;
							}
						}else{
							break;
						}
					}while(!(validatesalary(sal)));						
				
				//-------- INPUT BIRTHDAY -------------
				
					String bday="";
						do{
							System.out.print("B'Day(yyyy-MM-DD):\t:");
							bday=input.next();
							if(!(validatebday(bday))){
								System.out.println("\tInvalid Birthday....");
								System.out.print("Do you want to Enter Birthday again(Y/N): ");
								char op1_2=input.next().charAt(0);
								if(op1_2=='y' || op1_2=='Y'){
									System.out.print("\033[3A"); //ERASE 3 CODE LINES UPWARDS 
									System.out.print("\033[0J");
								}else{
									main(null);
									break;
								}
							}else{
								break;
							}
						}while(!(validatebday(bday)));
				
				Contact contact=new Contact(contactid,name,phone,cname,sal,bday);
				contactlist.add(contact);

				System.out.print("\t Contact has been added successfully...\n");
				
				System.out.print("Do you want to add another contact (Y/N):");
				op1=input.next().charAt(0);
				if(op1=='N' || op1=='n'){
					main(null);
					break;
				}else{
					continue;
				}
		
			}
			
		}else if(mainop==2){//------------ UPDATE CONTACTS -----------
			
			char op2='Y';
			while(op2=='y' || op2=='Y'){
				clearConsole();
				System.out.println("+---------------------------------------+ ");
				System.out.println("|\t\tUPDATE CONTACTS\t\t|");
				System.out.println("+---------------------------------------+ \n\n");
				
				System.out.print("Search cntact by Name or Phone Number :");
				String searchby=input.next();
				int index =contactlist.SearchArray(searchby);
				if(index==-1){
					System.out.print("\033[1A"); 
					System.out.print("\033[0J"); 
				}
				
				if(index>-1){
					
					System.out.println("\tContact ID\t\t:"+contactlist.getTempArray()[index].getId());
					System.out.println("\tName\t\t\t:"+contactlist.getTempArray()[index].getName());
					System.out.println("\tPhone Number\t\t:"+contactlist.getTempArray()[index].getPhone());
					System.out.println("\tCompany Name\t\t:"+contactlist.getTempArray()[index].getCompany());
					System.out.println("\tSalary\t\t\t:"+contactlist.getTempArray()[index].getSalary());
					System.out.println("\tB'Day(YYYY-MM-DD)\t:"+contactlist.getTempArray()[index].getBday());
					
					
					System.out.println("What Do You Want To Update...\n");
					
					System.out.println("\t[01]NAME");
					System.out.println("\t[02]PHONE NUMBER");
					System.out.println("\t[03]COMPANY NAME");
					System.out.println("\t[04]SALARY");
					
					System.out.print("Enter An Option To continue : ");
					int op2_1=input.nextInt();
					
					if(op2_1==1){
						System.out.println("UPDATE NAME : ");
						System.out.println("==============\n\n");
						System.out.print("Enter new name : ");
						contactlist.getTempArray()[index].setName(input.next());
						System.out.println("\n\tContact has been updated successfully...");
					}else if(op2_1==2){
						System.out.println("UPDATE PHONE NUMBER: ");
						System.out.println("=================\n\n");
						String newphone="";
						do{
							System.out.print("Enter new Phone Number\t\t:");
							newphone=input.next();
							if(!(validatephone(newphone))){ // VALIDATE PHONE NUMBER
								System.out.println("\tInvalid phone number....");
								System.out.print("Do you want to Enter phone number again(Y/N): ");
								char op2_2_1=input.next().charAt(0);
								if(op2_2_1=='y' || op2_2_1=='Y'){ // IF ENTERED Y GO ENTER OHONE NUMBER AGAIN
									System.out.print("\033[3A"); //ERASE 3 CODE LINES UPWARDS 
									System.out.print("\033[0J"); 
								}else{
									main(null);// IF ENTERED N GO TO THE MAIN PAGE
									break;
								}
							}else{
								contactlist.getTempArray()[index].setPhone(newphone);
							}	
						}while(!validatephone(newphone));// UNTIL THE CONDITION BECOMES TRUE LO0P WILL RUN
					}else if(op2_1==3){
						System.out.println("UPDATE CONTACT NUMBER : ");
						System.out.println("==============\n\n");
						System.out.print("Enter new Company name : ");
						String newcname=input.next();
						contactlist.getTempArray()[index].setCompany(newcname);
					}else if(op2_1==4){
						System.out.println("UPDATE SALARY: ");
						System.out.println("==============\n\n");
						int newsal=0;
						do{
							System.out.print("Enter new salary:");
							newsal=input.nextInt();
							if(!(validatesalary(newsal))){
								System.out.println("\tInvalid Salary....");
								System.out.print("Do you want to Enter Salary again(Y/N): ");
								char op2_2_2=input.next().charAt(0);
								if(op2_2_2=='y' || op2_2_2=='Y'){
									System.out.print("\033[3A"); //ERASE 3 CODE LINES UPWARDS 
									System.out.print("\033[0J");
								}else{
									main(null);
									break;
								}
							}else{
								contactlist.getTempArray()[index].setSalary(newsal);
							}
						}while(!(validatesalary(newsal)));	
						
					}
					
					System.out.print("Do You Want To Update Another Contact(Y/N) : ");
					op2=input.next().charAt(0);
					if(op2=='N' || op2=='n'){
						main(null);
						break;
					}else{
						continue;
					}				
				}
			}
			
		}else if(mainop==3){//------------ DELETE CONTACTS -----------
			
			char op3='Y';
			while(op3=='y' || op3=='Y'){
				clearConsole();
				System.out.println("+---------------------------------------+ ");
				System.out.println("|\t\tDELETE CONTACTS\t\t|");
				System.out.println("+---------------------------------------+ \n\n");
				
				System.out.print("Search cntact by Name or Phone Number :");
				String searchby=input.next();
				int index =contactlist.SearchArray(searchby);
				if(index==-1){
					System.out.print("\033[1A"); 
					System.out.print("\033[0J"); 
				}
				
				if(index>-1){
					
					System.out.println("\tContact ID\t\t:"+contactlist.getTempArray()[index].getId());
					System.out.println("\tName\t\t\t:"+contactlist.getTempArray()[index].getName());
					System.out.println("\tPhone Number\t\t:"+contactlist.getTempArray()[index].getPhone());
					System.out.println("\tCompany Name\t\t:"+contactlist.getTempArray()[index].getCompany());
					System.out.println("\tSalary\t\t\t:"+contactlist.getTempArray()[index].getSalary());
					System.out.println("\tB'Day(YYYY-MM-DD)\t:"+contactlist.getTempArray()[index].getBday());
					
					
					System.out.print("\nDo You Want To Delete This Contact(Y/N) :");
					char op3_1=input.next().charAt(0);
					
					System.out.print("\n\tCustomer has been deleted successfully...");
					if(op3_1=='Y' || op3_1=='y'){
						contactlist.DeleteArrayMain(index);
					}else{
						continue;
					}
						
					
					System.out.println("\nDo You Want To Update Another Contact(Y/N) : ");
					op3=input.next().charAt(0);
					if(op3=='N' || op3=='n'){
						main(null);
						break;
					}else{
						continue;
					}
				}
			}
		}else if(mainop==4){//------------ SEARCH CONTACTS -----------
			
			char op4='Y';
			while(op4=='Y' | op4=='N'){
				clearConsole();
				System.out.println("+---------------------------------------+ ");
				System.out.println("|\t\tSEARCH CONTACTS\t\t|");
				System.out.println("+---------------------------------------+ \n\n");
				
				System.out.print("Search cntact by Name or Phone Number :");
				String searchby=input.next();
				int index = contactlist.SearchArray(searchby);
				if(contactlist.SearchArray(searchby)==-1){
					System.out.println("\tInvalid search index....");
					System.out.print("Do you want to Enter search index again(Y/N): ");
					char op4_1=input.next().charAt(0);
					if(op4_1=='y' || op4_1=='Y'){ // IF ENTERED Y GO ENTER OHONE NUMBER AGAIN
						System.out.print("\033[3A"); //ERASE 3 CODE LINES UPWARDS 
						System.out.print("\033[0J"); 
					}else{
						main(null);
						break; 
					}
				}else if(index>-1){
					
					System.out.println("\tContact ID\t\t:"+contactlist.getTempArray()[index].getId());
					System.out.println("\tName\t\t\t:"+contactlist.getTempArray()[index].getName());
					System.out.println("\tPhone Number\t\t:"+contactlist.getTempArray()[index].getPhone());
					System.out.println("\tCompany Name\t\t:"+contactlist.getTempArray()[index].getCompany());
					System.out.println("\tSalary\t\t\t:"+contactlist.getTempArray()[index].getSalary());
					System.out.println("\tB'Day(YYYY-MM-DD)\t:"+contactlist.getTempArray()[index].getBday());
					
			
					System.out.println("\nDo You Want To Search Another Contact(Y/N) : ");
					op4=input.next().charAt(0);
					if(op4=='N' || op4=='n'){
						main(null);
						break;
					}else{
						continue;
					}
				}
			}
			
		}else if(mainop==5){//------------ LIST CONTACTS -------------
			
			char op5='Y';
			while(op5=='Y' || op5=='y'){
				clearConsole();
				System.out.println("+---------------------------------------+ ");
				System.out.println("|\t\tLIST CONTACTS\t\t|");
				System.out.println("+---------------------------------------+ \n\n");
				
				System.out.println("\t[01]LISTING BY NAME");
				System.out.println("\t[02]LISTING BY SALARY");
				System.out.println("\t[03]LISTING BY BIRTHDAY");
				
				System.out.print("Enter an option to continue :");
				int op5_1=input.nextInt();
				if(op5_1==1){
					
					System.out.println("\t----------------------------------");
					System.out.println("\t|\tList Contacts By Name\t |");
					System.out.println("\t----------------------------------");
					System.out.println("\n");
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("| Contact ID   |     Name     |   Phone Number   |   Company name   |   Salary  |   BirthDay   |");
					System.out.println("------------------------------------------------------------------------------------------------");
					
					SortByName();
					System.out.println("------------------------------------------------------------------------------------------------");
					
					System.out.println("\n");
					System.out.println("\nDo You Want To Stay In This Page(Y/N) : ");
					op5=input.next().charAt(0);
					if(op5=='N' || op5=='n'){
						main(null);
						break;
					}else{
						continue;
					}
					
				}else if(op5_1==2){
					
					System.out.println("\t----------------------------------");
					System.out.println("\t|\tList Contacts By Salary\t |");
					System.out.println("\t----------------------------------");
					System.out.println("\n");
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("| Contact ID   |     Name     |   Phone Number   |   Company name   |   Salary  |   BirthDay   |");
					System.out.println("------------------------------------------------------------------------------------------------");
					
					SortBySalary();
					System.out.println("------------------------------------------------------------------------------------------------"); 
					
					System.out.println("\n");
					System.out.println("\nDo You Want To Stay In This Page(Y/N) : ");
					op5=input.next().charAt(0);
					if(op5=='N' || op5=='n'){
						main(null);
						break;
					}else{
						continue;
					}
					
				}else if(op5_1==3){
					
					SortBybday();
					System.out.println("\t-----------------------------------------");
					System.out.println("\t|\tList Contacts By Birthday\t|");
					System.out.println("\t-----------------------------------------");
					System.out.println("\n");
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("| Contact ID   |     Name     |   Phone Number   |   Company name   |   Salary  |   BirthDay   |");
					System.out.println("------------------------------------------------------------------------------------------------");
					for(int i=0;i<contactArray.length;i++){
						System.out.printf("| %-12s | %-12s | %-16s | %-16s | %-9.2f | %-12s |\n",contactArray[i].getId(),contactArray[i].getName(),contactArray[i].getPhone(),contactArray[i].getCompany(),contactArray[i].getSalary(),contactArray[i].getBday());
					}
					System.out.println("------------------------------------------------------------------------------------------------");
					
					System.out.println("\n");
					System.out.println("\nDo You Want To Stay In This Page(Y/N) : ");
					op5=input.next().charAt(0);
					if(op5=='N' || op5=='n'){
						main(null);
						break;
					}else{
						continue;
					}
					
				}
			}
		
		}else if(mainop==6){//------------ CLOSE THE PROGRAM ----------
			return;
		}
	}
}
