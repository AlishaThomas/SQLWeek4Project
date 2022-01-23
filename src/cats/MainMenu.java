package cats;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MainMenu {
	//@formatter:off
	//@formatter:on
	
	private Scanner scanner = new Scanner(System.in);
	private CatDao catDao = new CatDao();
	
	private List<String> operations = List.of(
			"1. Add a cat to the table.",
			"2. List the cats.",
			"3. Change cat name.",
			"4. Remove a cat."		
			);

	public static void main(String[] args) {
		new MainMenu().runMenu();
	}
	
	private void runMenu() {
		boolean done = false;
		
		while(!done) {
			try {
				
			int selection = getUserSelection();
				
			switch(selection) {
			case -1:
				done = exitMenu();
			break;
			
			case 1:
				addCat();
			break;
			
			case 2:
				listCats();
			break;
			
			case 3:
				changeCatName();
			break;
			
			case 4:
				removeCat();
			break;
			
			default:
				System.out.println(selection + "is invalid. Please make another selection");
			break;
			
			}
				
				
			}
			catch(Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}
	

	private void removeCat() {
		listCats();
		Integer catId = getIntSelection("Enter the Id of the cat to remove from table.");
		
		catDao.removeCat(catId);
		System.out.println("Cat with ID = " + catId + "has been removed.");
	}

	private void changeCatName() {
		listCats();
		
		Integer catId = getIntSelection("Enter the cat ID to rename.");
		String catName = getStringSelection("Enter the new cat name.");
		
		Cat cat = new Cat();
		cat.setCatId(catId);
		cat.setCatName(catName);
		
		catDao.changeCatName(cat);
		System.out.println("Cat name changed to: " + catName);
	}

	private void listCats() {
		System.out.println("Here are the cats: ");
		catDao.fetchAllCats().forEach(cat -> System.out.println("   " + cat));
	}

	private void addCat() {
		String catName = getStringSelection("Enter a Cat name.");
		
		Cat cat = new Cat();
		cat.setCatName(catName);
		
		catDao.addCat(cat);
		System.out.println("Added cat " + cat);
	}

	private boolean exitMenu() {
		System.out.println("Goodbye!");
		return true;
	}

	private int getUserSelection() {
		printMenu();
		Integer selection = getIntSelection("Select an option from the menu. (Press Enter to quit.)");
		return Objects.isNull(selection) ? -1 : selection;
	}
	
	
	private void printMenu() {
		System.out.println("Menu:\n");
		operations.forEach(line -> System.out.println(line));
		
	}

	private Integer getIntSelection(String prompt) {
		String selection = getStringSelection(prompt);
		
		if(Objects.isNull(selection)) {
			return null;
		}
		
		try {
			return Integer.valueOf(selection);
		}catch (NumberFormatException e) {
			throw new RuntimeException(selection + "is not valid.");
		}
	}

	private String getStringSelection(String prompt) {
		System.out.print(prompt);
		String line = scanner.nextLine();
		
		return line.isBlank() ? null: line.trim();
	}

}
