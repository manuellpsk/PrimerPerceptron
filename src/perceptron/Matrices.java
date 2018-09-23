package perceptron;
public class Matrices {

    public static void main(String args[]){
    //las matrices a y b son n*2
    double []a={0,1};
    double []b={0,1};
    
    double [][]c=multiplicarConjuntos(a, b);
    
        for(int i=0; i<c.length; i++){
            for(int j=0; j<c[i].length; j++){
                System.out.print(c[i][j]+" ");
            }
            System.out.println("");
        }
        
    }
    private static double[][] multiplicarConjuntos(double []a, double[]b){
        
        double [][]resultado=new double[a.length*b.length][2];//matriz de n*2 para resultante
        
        for(int i=0; i<2; i++){
            //i=0 hace referencia a la primera columna de la matriz resultante, que tiene unicamente elementos del cojunto A.
            switch(i){
                case 0:
                    int ca=0,pa=0;
                    for(int j=0; j<resultado.length; j++){
                        ca++;
                        resultado[j][i]=a[pa];
                        if(ca==b.length){
                            pa++;
                            ca=0;
                        }
                    }
                    break;
                case 1:
                    int cb=0;
                    for(int j=0; j<resultado.length; j++){
                        resultado[j][i]=b[cb];
                        cb++;
                        if(cb==b.length){
                            cb=0;
                        }
                    }
                    break;
            }
        }
    return resultado;
    } 
    
}
