#include<stdio.h>
#include<conio.h>
  
int main()
{
  
    int n,a,b,prime;
    printf("Enter N");
    scanf("%d",&n);
    for(a=2;a<=n;a++)
    {
        prime=0;
        for(b=2;b<=i/2;b++)
        {
            if(a%b==0)
            {
                 prime=1;
                 break;
            }
        }
          
        if(prime==0 && n!= 1)
            printf("%d ",a);
    } 
}
