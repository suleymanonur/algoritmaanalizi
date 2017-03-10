#include <cstdlib>
#include <iostream>




using namespace std;

void yazdir(int dizi[], int elemanSayisi){
     int i;
     for(i=elemanSayisi/2;i>0; i--){
         cout << dizi[i-1] << " ";        
    
    }  
    cout << endl;
    
    for(i=elemanSayisi/2;i<elemanSayisi; i++){
         cout << dizi[i] << " ";         
                  
    }         

     
}
int main(int argc, char *argv[])
{
    int dizi[] = {4,-3,2,1,6,-1,-2,4};
    yazdir(dizi,8);
         
    system("PAUSE");
    return EXIT_SUCCESS;
}
