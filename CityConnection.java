import java.util.*;

class Connection {
	String city1;
	String city2;
	int cost;
	Connection (String city1, String city2, int cost) {
		this.city1 = city1;
		this.city2 = city2;
		this.cost = cost;
	}
	public void printConnection() {
		System.out.println("{ " + this.city1 + " , " + this.city2 + "} " + this.cost);
	}
}
public class CityConnection {
	public static void main(String[] args) {
		Connection[] citys = new Connection[10];
        citys[0] = new Connection("A","B",100);
        citys[1] = new Connection("B","C",56);
        citys[2] = new Connection("C","D",5);
        citys[3] = new Connection("B","F",22);
        citys[4] = new Connection("A","C",89);
        citys[5] = new Connection("C","E",8);
        citys[6] = new Connection("C","F",7);
        citys[7] = new Connection("C","M",32);
        citys[8] = new Connection("D","F",5);
        citys[9] = new Connection("G","D",11);
        ArrayList<Connection> connectionList = new ArrayList<>(Arrays.asList(citys));
        List<Connection> minCostPaths = findMinPaths(connectionList);
        if (minCostPaths != null) {
            for (Connection cn : minCostPaths) {
                cn.printConnection();
            }
        }
	}
	public static List<Connection> findMinPaths(List<Connection> connectionList) {
		List<Connection> result = new ArrayList<>();
		if (connectionList == null || connectionList.size() == 0) {
			return result;
		}
		Set<String> visited = new HashSet<>();
		Set<String> allcities = new HashSet<>();
		for (Connection cn : connectionList) {
			allcities.add(cn.city2);
			allcities.add(cn.city1);
		}
		Collections.sort(connectionList, new Comparator<Connection>() {
			@Override
			public int compare(Connection c1, Connection c2) {
				return c1.cost - c2.cost;
			}
		});
		while (connectionList.size() != 0) {
			Connection minCostConnection = findMinCostConnection(connectionList, visited);
			if (minCostConnection == null) {
				break;
			}
			visited.add(minCostConnection.city1);
			visited.add(minCostConnection.city2);
			result.add(minCostConnection);
			connectionList.remove(minCostConnection);
		}
		if (allcities.size() != visited.size()) {
			return null;
		}
		Collections.sort(result, new Comparator<Connection>() {
			@Override
			public int compare(Connection c1, Connection c2) {
				if (c1.city1.equals(c2.city1)) {
					return c1.city2.compareTo(c2.city2);
				}
				return c1.city1.compareTo(c2.city1);
			}
		});
		return result;
	}
	private static Connection findMinCostConnection(List<Connection> connectionList, Set<String> visited) {
		Connection minCostConnection = null;
		for (Connection cn : connectionList) {
			if (visited.size() == 0) {
				minCostConnection = cn;
				break;
			}
			if (visited.contains(cn.city1) ^ visited.contains(cn.city2)) {
				minCostConnection = cn;
				break;
			}		
		}
		return minCostConnection;
	}

}