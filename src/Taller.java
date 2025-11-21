import java.util.*;

public class Taller {

    private Estado e;
    private Solucion solucion;


    /*
    Árbol de exploración

                Estado inicial
                      [ ]
                       |
        -----------------------------------------------------------------
        |                       |                      |                |
       [M1]                    [M2]                   [M3]            [M4]  para este ejemplo usamos hasta M4 pero
        |                       |                      |               |     sería la N cantidad de máquinas que haya
      -----------------     ---------                      ...
      |        |           |        |
   [M1, M1] [M1, M2]... [M2, M1] [M2, M2]  ....
       |             .                  .
  [M1, M1, M1]...    .                  .
                     .                  .
                  [M1, M3, M4]       [M2, M2, M3, M4, M4]
                   Estado final       Estado final (total de piezas 12)
                   Estado solucion    Estado solucion (no la mejor)
                   (posible mejor)
*/

    public Solucion construirPiezasBacktracking(List<Maquina> maquinasList, int totalPiezasAConstruir){
        e = new Estado(0,  new LinkedList<Maquina>());
        solucion = new Solucion(Integer.MAX_VALUE, 0, new LinkedList<>(), 0);
        backtracking(e, maquinasList, totalPiezasAConstruir);
        // si no hay solcion devolver nulo.
        return solucion;
    }

    private void backtracking(Estado e, List<Maquina> maquinasList, int totalPiezas){
        e.cantidadDeEstados++;
        if(e.piezasCreadas == totalPiezas){
            if (e.maquinasPrendidas < solucion.getCantMaquinasEncendidas()){
                solucion.setSolucion(e);
            }
        }else{
            // La poda
            for (Maquina m : maquinasList) {
                e.prender(m);
                if (!poda(e, totalPiezas)){
                    backtracking(e, maquinasList, totalPiezas);
                }
                e.apagar(m);
            }
        }
    }
    
    private boolean poda(Estado e, int totalPiezas){
        return e.piezasCreadas > totalPiezas;
    }

    /** GREEDY -------------------------------------------------------------------------------------------- */
    /**
     * Estrategia para la construcción de piezas:
     * 
     * - Candidatos:
     *      Cada máquina disponible representa un candidato posible para ser
     *      utilizado en la construcción de las piezas.
     * 
     * - Estrategia de selección:
     *      Se ordenan las máquinas en orden descendente según su capacidad de
     *      producción.
     *      Se selecciona primero las máquinas de mayor capacidad, ya que permite
     *      alcanzar el objetivo con la menor cantidad de encendidos posibles.
     * 
     * - Criterio de aceptación:
     *      Mientras la suma de piezas construidas no exceda la cantidad total
     *      requerida, se enciende la máquina actual tantas veces como sea posible.
     *      Luego se continua con la siguiente máquina del listado.
     * 
     * - Consideraciones respecto a encontrar o no solución:
     *      Si la suma total de piezas producidas es exactamente igual a la
     *      requerida, se considera que se encontró una solución posible.
     *      En caso contrario, si no es posible alcanzar exactamente el total
     *      solicitado, no se devuelve solución (retorna null).
     */
    public Solucion construirPiezasGreedy(List<Maquina> maquinasList, int totalPiezas) {
        if (maquinasList == null || maquinasList.isEmpty() || totalPiezas <= 0) {
            return null;
        }

        Collections.sort(maquinasList);

        Estado e = new Estado(0, new ArrayList<>());
        this.solucion = new Solucion(0, 0, new ArrayList<>(), 0);

        greedy(e, maquinasList, totalPiezas);
        return this.solucion;
    }

    private void greedy(Estado e, List<Maquina> maquinasList, int totalOriginal){
        int objetivo = totalOriginal;

        Iterator<Maquina> it = maquinasList.iterator();
        while(it.hasNext() && objetivo > 0){
            e.cantidadDeEstados++;
            Maquina m = it.next();

            while(m.getCapacidad() <= objetivo){
                e.prender(m);
                objetivo -= m.getCapacidad();
            }
        }
        
        if(objetivo == 0){
            this.solucion.setSolucion(e);
        }
    }
}
