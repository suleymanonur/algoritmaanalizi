#include <cstdlib>
#include <iostream>
#include <vector>

using namespace std;

void yazdir(int dizi[],int l)
{
     int t1=0,t2=0;
     int tut1=dizi[l/2-1], tut2=dizi[l/2];
     int i,j;
     for(i=l/2-1;i>=0;i--)
     {
         t1=t1+dizi[i];
         if(tut1<t1) tut1=t1;
         cout<<dizi[i]<<",";
     }
     cout<<endl;
     for(i=l/2;i<l;i++)
     {
         t2=t2+dizi[i];
         if(tut2<t2) tut2=t2;
         cout<<dizi[i]<<",";
     }
     cout<<endl<<tut1<<endl<<tut2<<endl;
}

int main(int argc, char *argv[])
{
    int dizi[]={4,-3,2,1,6,-1,-2,4};
    yazdir(dizi,8);
    system("PAUSE");
    return EXIT_SUCCESS;
}
