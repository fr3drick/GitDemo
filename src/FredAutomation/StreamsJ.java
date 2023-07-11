package FredAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.annotations.Test;

public class StreamsJ {

	@Test
	public void streamsJ() {

		// regular();

		ArrayList<String> names = new ArrayList<>();
		names.add("Ada");
		names.add("Eze");
		names.add("Austin");
		names.add("John");
		names.add("Amos");
		names.add("Yomi");
		names.add("Abas");

		long c = names.stream().filter(s -> s.startsWith("A")).count();
		System.out.println(c);

		long d = Stream.of("Ada", "Eze", "Austin", "Amos", "Yomi", "John").filter(s -> {
			return s.startsWith("A");

		}).count();
		System.out.println(d);

		names.stream().filter(s -> s.length() > 3).forEach(s -> System.out.println(s));
		System.out.println("----------------------------");
		names.stream().filter(s -> s.length() > 3).limit(2).forEach(s -> System.out.println(s));
		System.out.println("*******************");
		names.stream().filter(s -> true).forEach(s -> System.out.println(s));
		System.out.println("#########################");
		names.stream().filter(s -> s.length() > 3).forEach(s -> System.out.println(s.toUpperCase()));
		System.out.println("#########################");
		boolean flag = names.stream().sorted().anyMatch(s -> s.equalsIgnoreCase("Ada"));
		System.out.println(flag);
		names.stream().sorted().forEach(s -> System.out.println(s));
		System.out.println("#########################");

		Stream<String> newStream = names.stream();
		newStream.forEach(s -> System.out.println(s));
		// newStream.sorted();
		// newStream.forEach(s->System.out.println(s));

		List<String> ls = names.stream().filter(s -> s.length() > 3).map(s -> s.toUpperCase())
				.collect(Collectors.toList());
		System.out.println(ls.get(0));

		// print unique numbers from list of integers and sort
		List<Integer> ints = Arrays.asList(3, 2, 2, 7, 5, 1, 9, 7);
		ints.stream().distinct().sorted().forEach(s -> System.out.println(s));
		List<Integer> ints2 = ints.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println("iiiiiiiiiiiiiiiiiiiiii");
		System.out.println(ints2.get(3));

	}

	static void regular() {
		ArrayList<String> names = new ArrayList<>();
		names.add("Ada");
		names.add("Eze");
		names.add("Austin");
		names.add("John");
		names.add("Amos");
		names.add("Yomi");
		names.add("Abas");

		int count = 0;

		for (String name : names) {
			if (name.startsWith("A"))
				count++;

		}
		System.out.println(count);

	}

}
