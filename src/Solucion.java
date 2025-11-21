import java.util.*;

public class Solucion {

    private List<Maquina> secuenciaMaquinasPrendidas;
    private int cantPiezasProducidas;
    private int cantMaquinasEncendidas;
    private int cantidadDeEstados;

    public Solucion(int cantMaquinasEncendidas, int cantPiezasProducidas, List<Maquina> secuenciaMaquinasPrendidas, int cantidadDeEstados) {
        this.cantMaquinasEncendidas = cantMaquinasEncendidas;
        this.cantPiezasProducidas = cantPiezasProducidas;
        this.secuenciaMaquinasPrendidas = new ArrayList<Maquina>(secuenciaMaquinasPrendidas);
        this.cantidadDeEstados = cantidadDeEstados;
    }

    public int getCantMaquinasEncendidas() {
        return cantMaquinasEncendidas;
    }

    public void setCantMaquinasEncendidas(int cantMaquinasEncendidas) {
        this.cantMaquinasEncendidas = cantMaquinasEncendidas;
    }

    public int getCantPiezasProducidas() {
        return cantPiezasProducidas;
    }

    public void setCantPiezasProducidas(int cantPiezasProducidas) {
        this.cantPiezasProducidas = cantPiezasProducidas;
    }

    public List<Maquina> getSecuenciaMaquinasPrendidas() {
        return secuenciaMaquinasPrendidas;
    }

    public void setSecuenciaMaquinasPrendidas(List<Maquina> secuenciaMaquinasPrendidas) {
        this.secuenciaMaquinasPrendidas = new LinkedList<Maquina>(secuenciaMaquinasPrendidas);
    }

    public int getCantidadDeEstados() {
        return cantidadDeEstados;
    }

    public void setSolucion(Estado e){
        this.clearSolucion();

        this.secuenciaMaquinasPrendidas = new ArrayList<>(e.secuenciaMaquinasPrendidas);
        this.cantPiezasProducidas = e.piezasCreadas;
        this.cantMaquinasEncendidas = e.maquinasPrendidas;
        this.cantidadDeEstados = e.cantidadDeEstados;
    }

    private void clearSolucion(){
        this.secuenciaMaquinasPrendidas.clear();
        this.cantPiezasProducidas = 0;
        this.cantMaquinasEncendidas = 0;
        this.cantidadDeEstados = 0;
    }

    @Override
    public String toString() {
        if (secuenciaMaquinasPrendidas == null || secuenciaMaquinasPrendidas.isEmpty()){
            return "No se encontró solución";
        }
        
        return "Solucion: " +
                "\n cantMaquinasEncendidas= " + cantMaquinasEncendidas +
                "\n secuenciaMaquinasPrendidas= " + secuenciaMaquinasPrendidas+
                "\n cantPiezasProducidas= " + cantPiezasProducidas;
    }
}