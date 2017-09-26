import java.util.*;

public class firstDeliverable
{
 public static void main(String[] args)
{
Scanner scanner = new Scanner(System.in);

int a = 0;
int b = 0;
int c = 0;

String string1, string2 ;
boolean bool ;

System.out.println("Enter two numbers to be added together:" );
a = scanner.nextInt();
scanner.nextLine();
b = scanner.nextInt();
scanner.nextLine();
c=a+b;
System.out.println("The result is " + c );
System.out.println("Enter a word of six letters or less" );
string1 = scanner.nextLine();
System.out.println("Enter a second word of six letters or less" );
string2 = scanner.nextLine();
bool= 
string1.equals(string2);

if (bool) 
{
System.out.println("The words entered are identical" );
}
else
{
System.out.println("The words entered are different" );
}

}

}
