import java.io.*;
import java.util.*;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            int total = Integer.parseInt(br.readLine().trim());
            List<Maquina> maquinas = new ArrayList<>();

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                maquinas.add(new Maquina(Integer.parseInt(partes[1].trim()), partes[0].trim()));
            }
            br.close();

            Taller taller = new Taller();
            Solucion sol1 = taller.construirPiezasBacktracking(maquinas, total);

            System.out.println("BACKTRACKING");
            System.out.println(sol1.toString());
            System.out.println(" cantidad de estados generados= " + sol1.getCantidadDeEstados());
            System.out.println();

            Solucion sol2 = taller.construirPiezasGreedy(maquinas, total);
            System.out.println("GREEDY");
            System.out.println(sol2.toString());
            System.out.println(" cantidad de candidatos considerados= " + sol2.getCantidadDeEstados());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

