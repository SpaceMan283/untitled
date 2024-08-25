


import sk.upjs.paz.graph.Edge;
import sk.upjs.paz.graph.Graph;
import sk.upjs.paz.graph.Vertex;

import java.util.*;

public class kostra {

    private static Set<Edge> primPriority(Graph g, Vertex s) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o.getDoubleValue("D")));
        for (Vertex vertex : g.getVertices()) {
            vertex.setValue("D", Double.POSITIVE_INFINITY);
            vertex.setValue("P", null);
        }
        s.setValue("D", 0.0);
        for (Vertex vertex : g.getVertices()) {
            pq.offer(vertex);
        }

        while (!pq.isEmpty()) {
            Vertex v = pq.poll();
            for (Vertex w : v.getNeighbours()) {
                double vaha = g.getEdge(v, w).getWeight();
                if (pq.contains(w) && vaha < w.getDoubleValue("D")) {
                    w.setValue("P", v);
                    w.setValue("D", vaha);
                    pq.remove(w);
                    pq.offer(w);
                }
            }
        }
        Set<Edge> result = new HashSet<>();
        for (Vertex vertex : g.getVertices()) {
            if (vertex.getValue("P") != null) {
                Vertex predchodca = (Vertex) vertex.getValue("P");
                result.add(g.getEdge(vertex, predchodca));
            }
        }


        return result;
    }

    public static Set<Edge> prim(Graph g, Vertex s) {
        Map<Vertex, Double> d = g.createVertexMap(Double.POSITIVE_INFINITY);
        //d.put(s, 0.0);
        Map<Vertex, Vertex> p = g.createVertexMap(null);
        Map<Vertex, Boolean> mnozinaQ = g.createVertexMap(true);
        while (mnozinaQ.containsValue(true)) {
            Vertex min = null;
            for (Map.Entry<Vertex, Double> entry : d.entrySet()) {
                // overi, ci vrchol je v mnozine Q
                if (mnozinaQ.get(entry.getKey())) {
                    if (min == null || entry.getValue() < d.get(min)) {
                        min = entry.getKey();
                    }
                }
            }
            Vertex v = min;
            mnozinaQ.put(v, false);
            for (Vertex w : v.getNeighbours()) {
                double vaha = g.getEdge(v, w).getWeight();
                if (mnozinaQ.get(w) && vaha < d.get(w)) {
                    p.put(w, v);
                    d.put(w, vaha);

                }
            }
        }
        Set<Edge> result = new HashSet<>();
        for (Map.Entry<Vertex, Vertex> vertexVertexEntry : p.entrySet()) {
            // if (vertexVertexEntry.getKey() != start) {
            if (vertexVertexEntry.getValue() != null) {
                result.add(g.getEdge(vertexVertexEntry.getKey(), vertexVertexEntry.getValue()));
            }
        }
        return result;
    }

    public static Set<Edge> kruskal(Graph g) {
        Set<Edge> result = new HashSet<>();
        List<Edge> edges = new ArrayList<>(g.getEdges());
        List<Vertex> vertices = new ArrayList<>(g.getVertices());
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setValue("komponent", i);
        }
        // edges.sort(Comparator.comparingDouble(Edge::getWeight));
        Collections.sort(edges, new Comparator<Edge>(){
            @Override
            public int compare(Edge o1, Edge o2) {
                return Double.compare(o1.getWeight(), o2.getWeight());
            }
        });
        for (Edge edge : edges) {
            int k1 = edge.getSource().getIntValue("komponent");
            int k2 = edge.getTarget().getIntValue("komponent");
            if (k1 != k2) {
                result.add(edge);
            }
            int kMin = Math.min(k1, k2);
            int kMax = Math.max(k1, k2);
            for (Vertex vertex : vertices) {
                if (vertex.getIntValue("komponent") == kMax) {
                    vertex.setValue("komponent", kMin);
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        Set<Edge> a = primPriority(g, g.getVertex("A"));
        Set<Edge> b = prim(g, g.getVertex("A"));
        Set<Edge> c = kruskal(g);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }
}

