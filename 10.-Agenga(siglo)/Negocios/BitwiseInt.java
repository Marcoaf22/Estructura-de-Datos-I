package Negocios;

public class BitwiseInt {
int x;    
public BitwiseInt(){
    x=0;
}

public void Set1(int pos){  //Enciende un bit en el bitwise
   if (pos <=32){
    int mascara=1;   //lo inicializamos  con primer bit ala derecha la mascara 
    mascara=mascara<<pos-1; //y recoremos el 1 bit inicial entre el vector de bits
    x= x|mascara;  //sumamos x con la mascara con or
   }
}
public int getvalor(){
    return x;
}
public void Set0(int pos){//apaga el bit en el entero x
   if (pos<=64){ 
    int mascara=1;
    mascara=mascara<<pos-1; //llevamos el bit 1 hacia la posicion
    mascara=~mascara;
    x=x & mascara;          //una vez llevado hacemos not ala mascara y despues un and al x
   }
   }
public int getBit(int pos){//retorna si el bit en la pos es 0,1
   if (pos<=32){
    int mascara=1;
    mascara=mascara<<pos-1;//llevamos ala posicion deseada la mascara
    mascara=mascara & x;// y aplicamos un and
    mascara=mascara>>>pos-1; //despues de aplicar el and sabremos si el bit en la pos es 0,1 lo llevamos de nuevo al principio
    return mascara; 
   }
   return 0;
}
public void setX(int n){
    x=n;
}
public String toString(){
    String s=" X =";
    for (int i=32;i>=1;i--){
        s=s+" "+getBit(i);
    }
    return s+" = "+x;
}
public int Cant1(){
    int c=0;
    for (int i=1;i<=32;i++){
        if (getBit(i)==1)
          c++;  
    }
    return c;
}
public static void main(String[] args) {
     
     BitwiseInt x=new BitwiseInt();
     x.Set1(32);
     x.Set1(1);
     System.out.println(x.toString());
     System.out.println(x.getBit(32));
     System.out.println();
}
}

