#include <cstdlib>
#include <iostream>

using namespace std;

long pow(int x,int n)
{
     if(n==0) return 1;
     if(n==1) return x;
     if(n%2==0){
         return pow(x*x,n/2);
     }
     else{
         return pow(x*x,n/2) * x;
     }
}
int main(int argc, char *argv[])
{
    cout <<"taban";
    int t;
    cin >> t;
    cout <<"us";
    int u;
    cin >> u;
    int s;
    s=pow(t,u);
    cout << s;
    system("PAUSE");
    return EXIT_SUCCESS;
}
