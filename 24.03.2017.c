#include<stdio.h>
#include<conio.h>
#include<stdlib.h>
#include<time.h>

/*
 * 
 */

#define SENTINEL -100000000
int dizi[1000];
int asd=0;
struct ikili_arama_agaci *agacDizisi[10];
float derinlikToplam=0;
int toplamEleman=0;

struct dugum {
    int icerik;
    struct dugum *sol;
    struct dugum *sag;
};

struct ikili_arama_agaci{
    struct dugum *kok;
};

void ikili_arama_agaci_olustur(struct ikili_arama_agaci **agac){
    *agac=(struct ikili_arama_agaci*)malloc(sizeof(struct ikili_arama_agaci));
    if(*agac==NULL){
        printf("Heapte gerekli yer ayrilamadi... exit ...\n");
        exit(1);
    }
    (*agac)->kok=NULL;  
}

int ikili_agac_bosmu(struct ikili_arama_agaci *agac){
   if(agac->kok==NULL) return 1;
   else return 0;
}

struct dugum* dugum_olustur(int icerik){
    struct dugum *d = (struct dugum*)malloc(sizeof(struct dugum));
    if(d==NULL){
        printf("Heapte gerekli yer ayrilamadi... exit ...\n");
        exit(1);
    }
    d->icerik = icerik; (*d).icerik=icerik;
    d->sol=d->sag=NULL;
    return d;
}


void ekle(struct ikili_arama_agaci *agac,int icerik){
    struct dugum *dugum;
    struct dugum *d;
    struct dugum *geri;
    
    d=agac->kok;
    while(d!=NULL){
        geri=d;
        if(icerik < d->icerik) d=d->sol;
        else if(icerik > d->icerik) d= d->sag;
        else return;
    }
    dugum=dugum_olustur(icerik);
    if(agac->kok==NULL){
        agac->kok = dugum;
        return;
    }
    if(icerik < geri->icerik) geri->sol = dugum;
    else geri->sag = dugum;
    
}



void preorder_yardimci(struct dugum *kok){
    
    if(kok==NULL) return;
    
    printf("%4d ",kok->icerik);
    derinlikToplam+=derinlik(kok);
    
    preorder_yardimci(kok->sol);
    preorder_yardimci(kok->sag);
    
}
void preorder(struct ikili_arama_agaci *agac){
    if(agac==NULL) return;
    preorder_yardimci(agac->kok);
    printf("\n");
}



int dugum_sayisi(struct dugum *kok){
    if(kok==NULL) return 0;
    return 1+dugum_sayisi(kok->sol)+dugum_sayisi(kok->sag);
}





int derinlik(struct dugum *kok)
{
    int solUzunluk=0;
    int sagUzunluk=0;
    if(kok==NULL) return 0;
    else{
          solUzunluk=derinlik(kok->sol);
          sagUzunluk=derinlik(kok->sag);
    }
    if(solUzunluk>sagUzunluk)
    {
         return solUzunluk+1;
    }
    else{
         return sagUzunluk+1;
    }
         
}
int foo(struct dugum *kok){ // ic dugum sayisi
    if(kok==NULL) return 0;
    else if(kok->sol!=NULL || kok->sag!=NULL) 
        return 1+foo(kok->sol) + foo(kok->sag);
    else return 0;
}
int main(int argc, char** argv) {
    int n;
    srand(time(NULL));
    
    
    int randomSayi=rand()%3+1;
    printf("random sayisi=%d\n",randomSayi);
    for(n=0;n<randomSayi;n++){
        struct ikili_arama_agaci *agac;
        agac=agacDizisi[n];
        ikili_arama_agaci_olustur(&agac);
        ekle(agac,rand()%50);
        ekle(agac,rand()%50);
        ekle(agac,rand()%50);
        ekle(agac,rand()%50);
        preorder(agac);
        
    }
    toplamEleman=randomSayi*4;
    printf("Derinlik Ortalaması=%f",derinlikToplam/toplamEleman);
    //ekle(agac,25);
    /*ekle(agac,75);
    ekle(agac,20);
    ekle(agac,35);
    ekle(agac,98);
    ekle(agac,99);
    ekle(agac,500);
    ekle(agac,400);
    ekle(agac,300);
    ekle(agac,210);
    ekle(agac,375);
    ekle(agac,30);
    ekle(agac,173);*/
    //preorder(agac);
    //int toplamEleman=dugum_sayisi(agac->kok);
    
    //printf("Ic dugum sayisi: %4d\n",foo(agac->kok));
    //printf("derinlik sayisi: %4d\n",derinlik(agac->kok));
    
    int a;
    int i;
    float toplam=0;
    /*for(i=0;i<toplamEleman;i++)
    {
            toplam+=dizi[i];
    }*/
    /*printf("Derinlik Ortalamasi:%f/n",toplam/toplamEleman);*/
    scanf("%d", &a);
    
   
    
    return (EXIT_SUCCESS);
}
