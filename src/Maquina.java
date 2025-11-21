public class Maquina implements Comparable<Maquina> {

    private int capacidad;
    private String nombre;

    public Maquina(int capacidad, String nombre) {
        this.capacidad = capacidad;
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int compareTo(Maquina other){ // comparable para hacer el sort x capacidad
        return other.capacidad - this.capacidad;
    }

    public String toString() {
        return this.getNombre(); // Simplemente devuelve el nombre
    }

}
