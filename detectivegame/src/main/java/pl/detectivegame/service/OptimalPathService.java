package pl.detectivegame.service;

import es.usc.citius.hipster.algorithm.Algorithm;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.impl.WeightedNode;
import es.usc.citius.hipster.model.problem.SearchProblem;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.springframework.stereotype.Service;
import pl.detectivegame.model.Location;
import pl.detectivegame.model.LocationConnectionWithName;
import pl.detectivegame.model.OptimalPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OptimalPathService {


    List<OptimalPath> calculateOptimalPaths(List<LocationConnectionWithName> paths, List<Location> locations){
        HipsterDirectedGraph<String, Integer> graph = createGraph(paths);
        HashMap<MultiKey, Integer> pathNumbers = new HashMap<>();
        createPathNumbersMap(paths, pathNumbers);
        List<OptimalPath> optimalPaths = new ArrayList<>();
        calculateOptimalPaths(locations, graph, optimalPaths, pathNumbers);
        return optimalPaths;
    }

    private void createPathNumbersMap(List<LocationConnectionWithName> paths, HashMap<MultiKey, Integer> pathNumbers) {
        int pathNumber = 0;
        for( LocationConnectionWithName path : paths){
            pathNumbers.put(new MultiKey(path.getLocation1(), path.getLocation2()),pathNumber);
            pathNumbers.put(new MultiKey(path.getLocation2(), path.getLocation1()),pathNumber);
            pathNumber++;
        }
    }

    private void calculateOptimalPaths(List<Location> locations, HipsterDirectedGraph<String, Integer> graph, List<OptimalPath> optimalPaths, HashMap<MultiKey, Integer> pathNumbers) {
        for(Location start : locations){
            SearchProblem p = GraphSearchProblem
                    .startingFrom(start.getName())
                    .in(graph)
                    .takeCostsFromEdges()
                    .build();
            for( Location end: locations){
                Algorithm.SearchResult search = Hipster.createDijkstra(p).search(end.getName());
                if (!start.getName().equals(end.getName())) {
                    List<Integer> optimalPathWithPathNumbers = convertLocationNamesPathToPathNumbers(pathNumbers, search);
                    OptimalPath optimalPath = OptimalPath.builder()
                            .location1(start.getName())
                            .location2(end.getName())
                            .optimalPath(optimalPathWithPathNumbers)
                            .cost((Double)((WeightedNode)search.getGoalNode()).getCost())
                            .build();
                    optimalPaths.add(optimalPath);
                }
            }
        }
    }

    private List<Integer> convertLocationNamesPathToPathNumbers(HashMap<MultiKey, Integer> pathNumbers, Algorithm.SearchResult search) {
        List<String> optimalPathWithLocationNames = search.getOptimalPaths().isEmpty() ? null : (List<String>)search.getOptimalPaths().get(0);
        if(optimalPathWithLocationNames == null ) return null;
        List<Integer> optimalPathWithPathNumbers = new ArrayList<>();
        for(int i=0; i<optimalPathWithLocationNames.size()-1; i++){
            optimalPathWithPathNumbers.add(pathNumbers.get(new MultiKey(optimalPathWithLocationNames.get(i), optimalPathWithLocationNames.get(i+1))));
        }
        return optimalPathWithPathNumbers;
    }

    private HipsterDirectedGraph<String, Integer> createGraph(List<LocationConnectionWithName> paths) {
        GraphBuilder<String,Integer> builder = GraphBuilder.create();
        for (LocationConnectionWithName connection : paths) {
            builder = builder.connect(connection.getLocation1()).to(connection.getLocation2()).withEdge(connection.getCost());
            builder = builder.connect(connection.getLocation2()).to(connection.getLocation1()).withEdge(connection.getCost());
        }
        return builder.createDirectedGraph();
    }

}
