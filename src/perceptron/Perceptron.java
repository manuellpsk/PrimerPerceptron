package perceptron;
public class Perceptron {
    public static void main(String[] args) {

        
        double [] x1={0,0,1,1};//entrada
        double [] x2={0,1,0,1};//entrada
        double [] yD={0,1,1,1};//salidas Deseadas
        //w[2]=umbral.
        double [] w={-1.5,0.99, 1/*<-- umbral*/};
        double [] wpass=new double[3];//array para almacenar pesos, para luego verificar si cambiaron
        double error=0;
        double n=0.25;//tasa de aprendizaje
        double aprendizaje;
        double []yR=new double[4];//array para los resultados reales obtenidos de la funcion sgn(x)
        int c=0;
        boolean flag=true;
        while(flag){
            for(int i=0;i<4;i++){
                //los pesos actuales los almaceno en otro array, para luego verificar si cambiaron o no.
                wpass[0]=w[0];wpass[1]=w[1];wpass[2]=w[2];
                //calculo x para f(x), para luego aplicar la funcion sgn(x)={1, si x>=0
                //                                                           0, si x< 0}
                double x= x1[i]*w[0] + x2[i]*w[1] - w[2];//termina con -w[2], porque siempre se agrega una entrada
                                                        //en todo perceptron, este caso la entrada x3 es siempre -1
                                                        //seria: +x3*w[2], con x3=-1 --> -w[2]
                System.out.println("Valor resultante:  "+x);
                c++;//contador, solo para saber cuantas veces se repitio.
                if(x>=0){
                    yR[i]=1;
                }else{
                    yR[i]=0;
                }
                //si la salida real es diferente a la salida deseada, recalcula pesos
                if(yR[i]!=yD[i]){
                    System.out.println("                Recalculando pesos...");
                        error=yD[i]-yR[i];
                    System.out.println("                Error de :"+error);
                    for(int j=0;j<w.length;j++){
                        //cuando j==2 significa que necesito la entrada x3[]=-1
                        switch(j){
                            case 0:
                                aprendizaje=n*error*x1[i];
                                w[j]=wpass[j]+aprendizaje;
                                break;
                            case 1:
                                aprendizaje=n*error*x2[i];
                                w[j]=wpass[j]+aprendizaje;
                                break;
                            case 2:
                                w[j]=wpass[j]-n*error;
                                break;
                        }
                    }
                    System.out.println("            Nuevos pesos: "+w[0]+" - "+w[1]+" - "+w[2]);
                }
            }
            
            flag=(yRincorrectos(yD, yR) || changeValuesArray(wpass, w));
            System.out.println(yRincorrectos(yD, yR));
            System.out.println(changeValuesArray(wpass, w));
            System.out.println(flag);
            System.out.println("---------------------------------------------------------------------------------------");
        }
        mostrar(yD);
        mostrar(yR);
        mostrar(wpass);
        mostrar(w);
        System.out.println("\nNumero de bucles: "+c);
        System.out.println("\nNumero de epocas: "+c/4);
        System.out.println("Pesos correctos: "+w[0]+" - "+w[1]+" - "+w[2]);
        testeo(0,0,w);
        testeo(0,0,w);
        testeo(0,0,w);
        testeo(0,-0.5,w);
    }
    
    private static boolean changeValuesArray(double []wpass,double []wnow){
    
        for(int i=0;i<wpass.length;i++){
            if(wpass[i]!=wnow[i]){
                return true;
            }
        }
        return false;
    }
    
    private static boolean yRincorrectos(double [] reales,double [] esperados){
        
        for(int i=0;i<reales.length;i++){
            if(reales[i]!=esperados[i]){
                return true;
            }
        }
        return false;
    }
    
    private static void testeo(double x1,double x2,double w[]){
    
        double x= x1*w[0] + x2*w[1] - w[2];

                if(x>=0){
                    System.out.println("1");
                }else{
                    System.out.println("0");
                }
    }

    private static void mostrar(double[] yD) {
        System.out.println("\n");
        for(double x:yD){
            
            System.out.print(x+", ");
        }
    }
    
}
