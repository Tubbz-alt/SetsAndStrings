package stringsPackage;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Strings {
	public static void main(String[] args) {

		int flag1 = 0; // for unknown elements
		int flag2 = 0; // for redundant elements
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);

		System.out.println("Enter number of elements you want in the Universe");
		int stringNum = scan.nextInt();
		while (stringNum == 0 || stringNum < 0) {
			System.out.println("Number not accepted , Enter a valid number");
			stringNum = scan.nextInt();
		}

		String stringArr[] = new String[stringNum];
		String stringName = new String();

		for (int i = 0; i < stringNum; i++) {
			System.out.println("Enter the element number " + (i + 1));
			stringArr[i] = scan2.nextLine();
			while (stringArr[i].equals(" ") || stringArr[i].equals("")) {
				System.out.println("Value is not accepted, enter another value");
				stringArr[i] = scan2.nextLine();
			}
			// checks no redundancy
			flag2 = 0;
			for (int u = i; u > 0; u--) {
				if (stringArr[i].equals(stringArr[u - 1])) {
					flag2 = 1;
					i--;
					System.out.println("Value already available, Enter another one.");
					break;
				}
			}
		}
		System.out.println(
				"Now the Universe is ready, Enter the number of subsets you want (Please enter only the values in the original Universe)");

		int subsetNum = scan.nextInt();
		LinkedList subsets[] = new LinkedList[subsetNum];
		int helpNum;
		String help;

		for (int i = 0; i < subsetNum; i++) {
			System.out.println("Enter the number of elements in Subset " + (i + 1));
			helpNum = scan.nextInt();
			while (helpNum > stringNum) {
				System.out.println("Elements can't be more than original set, Enter a less value");
				helpNum = scan.nextInt();

			}
			subsets[i] = new LinkedList();

			for (int x = 0; x < helpNum; x++) {
				System.out.println("Enter element number " + (x + 1) + " inside subset number " + (i + 1));
				help = scan2.nextLine();

				// checks no redundancy
				flag2 = 0;
				for (int u = x; u > 0; u--) {
					if (help.equals(subsets[i].get(u - 1))) {
						flag2 = 1;
						x--;
						System.out.println("Value already available, Enter another one.");
						break;
					}
				}
				flag1 = 0;
				// checks no unknown element
				for (int y = 0; y < stringNum; y++) {
					if (help.equals(stringArr[y]) || (help.equals(" ") && subsets[i].size() == 1)) {
						flag1 = 1;
						break;
					}
				}
				if (flag1 == 0) {
					System.out.println("Cannot add a value which is not in Universe");
					x--;//

				} else
					subsets[i].add(help);
			}

		}
		System.out.println(
				"You have succesfully added your subsets, Choose one of the operations to do and the number of the subset(s) you want");
		System.out.println("");
		int programchoice = 1;
		while (programchoice == 1) {
			System.out.println("Choose ONE of the operations please: 1-Union 2-Intersection 3-Complement");
			int choice = scan.nextInt();
			int numofsubset = 20;
			int numofsubset2 = 20;
			if (choice == 3) {
				System.out.println("Which subset do you want to complement?");
				while (numofsubset >= subsets.length) {
					numofsubset = scan.nextInt() - 1;
					if (numofsubset >= subsets.length) {
						System.out.println("Number of subset is invalid ,Enter again.");
					}
				}
				boolean maskSet[] = new boolean[stringArr.length];

				for (int x = 0; x < stringArr.length; x++) {
					maskSet[x] = false;
				}
				for (int i = 0; i < subsets[numofsubset].size(); i++) {
					for (int u = 0; u < stringArr.length; u++) {
						if (subsets[numofsubset].get(i).equals(stringArr[u])) {
							maskSet[u] = true;
							break;
						}
					}
				}
				boolean complement = false;
				for (int i = 0; i < stringArr.length; i++) {
					if (!maskSet[i]) {
						complement = true;
					}
				}
				if (stringArr.length == 1)
					complement = false;
				if (!complement) {
					System.out.println("This set has no complement");
				} else {
					System.out.println("Complement Set is :");
					System.out.print("{");
					int u = 0;
					for (int i = 0; i < stringArr.length; i++) {
						if (!maskSet[i]) {
							if (u == 0) {
								System.out.print(stringArr[i]);
							} else {
								System.out.print(", " + stringArr[i]);
							}
							u++;

						}
					}
					System.out.print("}");
				}
			}

			else if (choice == 1) {
				if (subsets.length == 1) {
					System.out.println("Only one set available, cannot get Union");
				} else {
					while (numofsubset >= subsets.length || numofsubset2 >= subsets.length) {
						System.out.println("Write the number of set 1");
						numofsubset = scan.nextInt() - 1;
						System.out.println("Write the number of set 2");
						numofsubset2 = scan.nextInt() - 1;
						if (numofsubset >= subsets.length || numofsubset2 >= subsets.length) {
							System.out.println("Number of subset(s) is invalid ,Enter again.");
						}
					}

					boolean maskSet[] = new boolean[stringArr.length];

					for (int x = 0; x < stringArr.length; x++) {
						maskSet[x] = false;
					}
					for (int i = 0; i < subsets[numofsubset].size(); i++) {
						for (int u = 0; u < stringArr.length; u++) {
							if (subsets[numofsubset].get(i).equals(stringArr[u])) {
								maskSet[u] = true;
								break;
							}
						}
					}
					for (int i = 0; i < subsets[numofsubset2].size(); i++) {
						for (int u = 0; u < stringArr.length; u++) {
							if (subsets[numofsubset2].get(i).equals(stringArr[u])) {
								maskSet[u] = true;
								break;
							}
						}

					}
					System.out.println("Union Set is :");
					System.out.print("{");
					int u = 0;
					for (int i = 0; i < stringArr.length; i++) {
						if (maskSet[i]) {
							if (u == 0) {
								System.out.print(stringArr[i]);
							} else {
								System.out.print(", " + stringArr[i]);
							}
							u++;
						}
					}
					System.out.print("}");

				}
			} else if (choice == 2) {
				if (subsets.length == 1) {
					System.out.println("Only one set available, cannot get Intersection");
				} else {
					boolean intersection = false;
					while (numofsubset >= subsets.length || numofsubset2 >= subsets.length) {
						System.out.println("Write the number of set 1");
						numofsubset = scan.nextInt() - 1;
						System.out.println("Write the number of set 2");
						numofsubset2 = scan.nextInt() - 1;
						if (numofsubset >= subsets.length || numofsubset2 >= subsets.length) {
							System.out.println("Number of subset(s) is invalid , Enter again.");
						}
					}

					int maskSet[] = new int[stringArr.length];

					for (int x = 0; x < stringArr.length; x++) {
						maskSet[x] = 0;
					}
					for (int i = 0; i < subsets[numofsubset].size(); i++) {
						for (int u = 0; u < stringArr.length; u++) {
							if (subsets[numofsubset].get(i).equals(stringArr[u]) && maskSet[u] == 0) {
								maskSet[u] = 1;
								break;
							} else if (subsets[numofsubset].get(i).equals(stringArr[u]) && maskSet[u] == 1) {
								maskSet[u] = 2;
								break;
							}
						}
					}
					for (int i = 0; i < subsets[numofsubset2].size(); i++) {
						for (int u = 0; u < stringArr.length; u++) {
							if (subsets[numofsubset2].get(i).equals(stringArr[u]) && maskSet[u] == 0) {
								maskSet[u] = 1;
								break;
							} else if (subsets[numofsubset2].get(i).equals(stringArr[u]) && maskSet[u] == 1) {
								maskSet[u] = 2;
								break;
							}
						}
					}
					for (int i = 0; i < maskSet.length; i++) {
						if (maskSet[i] == 2) {
							intersection = true;
						}
					}
					if (!intersection) {
						System.out.println("Set has no intersection");
					} else {
						System.out.println("Intersection Set is :");
						System.out.print("{");
						int u = 0;
						for (int i = 0; i < stringArr.length; i++) {
							if (maskSet[i] == 2) {

								if (u == 0) {
									System.out.print(stringArr[i]);
								} else {
									System.out.print(", " + stringArr[i]);
								}
								u++;
							}
						}
						System.out.print("}");

					}

				}

			}
			System.out.println();
			System.out.println("Enter 1 to continue and 0 to exit program.");
			programchoice = scan.nextInt();
		}
		System.out.println("System terminated.");
	}

}
