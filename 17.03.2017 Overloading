#include <cstdlib>
#include <iostream>

using namespace std;



class myClass
{
      public:
             int dizi[100];
             int sonraki;
             myClass::myClass()
             {
                 sonraki=0;
             }
      myClass myClass::operator=(myClass array){
         
         if(sonraki== array.sonraki){
                      for(int i=0;i<=sonraki;i++){
                      dizi[i]=array.dizi[i];        //Karmaşıklığı O(n) dir. For döngüsünden dolayı
                      }
         }      
         else{
              cout<<"hata";
              }           
       }
      void ekle(int a)
             {
                 dizi[sonraki]=a;         //karmaşıklık O(1) dir. Sadece atama işlemi yapılıyor.
                 sonraki++; 
             }        
};

int main(int argc, char *argv[])
{
    myClass obj1;
    myClass obj2;
    obj1.ekle(3);
    obj1.ekle(2);
    obj2.ekle(10);
    obj2.ekle(11);
    obj1=obj2;
    for(int i=0;i< obj1.sonraki; i++)
    {
            cout<<obj1.dizi[i];
            cout<<endl;
    } 
    system("PAUSE");
    return EXIT_SUCCESS;
}
