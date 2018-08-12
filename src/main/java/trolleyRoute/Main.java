package trolleyRoute;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Задача 116:  Вагонетка
Задача: В одной области одной северной страны, где нет нормальных дорог, чтобы добраться из одного поселка до другого люди вынуждены пользоваться самодельной вагонеткой для доставки грузов. 
В этой области N поселков и только нектр из них соединены между собой старой железной дорогой. 
Для проезда по ней из одного поселка до другого нужна одна канистра горючего, но в каждом поселке своя стоимость канистры горючего. 
А вагонетка слишком мала, и вы не можете взять с собой доп.канистры. 
Необходимо довести груз из 1го поселка до N-го, при этом использовать минимальное кол-во средств на горючку.

Входные данные: OilCosts = [oilCost_1, oilCost_2, ... oilCost_N] - массив, где oilCost(i) - стоимость канистры горючего в i-м поселке. 
TrainRoads = [ (Ai, Bj) ] - массив, где (Ai, Bj) - железная дорога, ктр соединяет поселок Ai c поселком Bj.
Дороги двухсторонние, и разумеется, между любыми двумя поселками не более 1 одной дороги.

Вывод: вывести оптимальный маршрут, т.е. номера поселков на этом маршруте.
Пример:
OilCosts = [5, 10, 1]

TrainRoads = [ (1, 3), (1, 2), (3, 2) ]

Answer: 1 -> 3	
*/

/* 
 * Задача решена 3-мя способами, с помощью классических алгоритмов поиска кратчайшего пути:
 * 1. Алгоритм Дейкстры
 * 2. Алгоритм Беллмана — Форда
 * 3. Алгоритм Флойда — Уоршелла
 * 
 * В качестве графа мы рассмотрим поселки и дороги между ними. Данный граф является взвешенным и ориентированным.
 * Вес ребра, соединяющего вершины i->j будет равен OilCosts[i] (стоимости канистры горючего в i-м поселке)
 */

public class Main {

	public static void main(String[] srgs) {		
		double[] oilCost = new double[] {5, 10, 1}; 
		Set<TrainRoad> trainRoads = new HashSet<>();
		trainRoads.add(new TrainRoad(1, 2));
		trainRoads.add(new TrainRoad(2, 3));
		trainRoads.add(new TrainRoad(3, 1));
		
		System.out.println("Алгоритм Дейкстры");
		DijkstraAlgo dk = new DijkstraAlgo(oilCost, trainRoads);
		print(dk.getRoute());
		
		System.out.println("Алгоритм Беллмана — Форда");
		BellmanFordAlgo bf = new BellmanFordAlgo(oilCost, trainRoads);
		print(bf.getRoute());
		
		
		System.out.println("Алгоритм Флойда — Уоршелла");
		FloydWarshallAlgo fw = new FloydWarshallAlgo(oilCost, trainRoads);
		print(fw.getRoute());	
	}
	
	public static void print(List<Integer> cities) {		
		cities.forEach(i -> System.out.print(i + " "));
		System.out.println();
	}
}
