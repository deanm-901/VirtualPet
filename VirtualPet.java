import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Pet {
   public String name;
   public int happiness;
   public int hunger;
   public int energy;

   public void feed() {
      this.happiness += 1;
      this.hunger    -= 3;
   }

   public void play() {
      this.happiness += 3;
      this.hunger    += 2;
      this.energy    -= 2;
   }

   public void sleep() {
      this.happiness -= 1;
      this.hunger    += 1;
      this.energy    += 3;
   }

   public void bath() {
      this.happiness += 2;
      this.hunger    += 1;
      this.energy    -= 1;
   }

   public void displayAttr() {
      System.out.println("-------------------------");
      System.out.println("Happiness: "+this.happiness);
      System.out.println("   Hunger: "+this.hunger);
      System.out.println("   Energy: "+this.energy);
      System.out.println("-------------------------");
   }

   public bool isOkay() {
      return this.happiness < 15 || this.hunger < 10 || energy > 0;
   }
}

public class VirtualPet {
   public static final enum Choices {
      FEED(1) {
         @Override
         public String[] applyChoice(Pet pet) {
            pet.feed();
            return {
               "Yum! They loved it!",
               "Oh god, they threw it up!",
               "They thoroughly enjoed it.",
               "It was so good, they did a backflip!"
            };
         }
      },
      PLAY(2) {
         @Override
         public String[] applyChoice(Pet pet) {
            pet.play();
            return {
               "You went to a pet park and they made friends!",
               "You decided to go on a walk with your pet. They pooped everywhere.",
               "You gave them the toys you bought for them earlier! They ripped it all up.",
               "You let them go to the backyard to play with some birds. The birds didnt like them."
            };
         }
      },
      SLEEP(3) {
         @Override
         public String[] applyChoice(Pet pet) {
            pet.sleep();
            return {
               "They looked like they were dreaming of steak you ate earlier.",
               "Aah! they got woken up by a nightmare! But they seems happy.",
               "They look like they're dreaming of chasing spiders.",
               "They woke up at night and woke you up!"
            };
         }
      },
      BATH(4) {
         @Override
         public String[] applyChoice(Pet pet) {
            pet.bath();
            return {
               "They liked the bath",
               "Oh god, they pooped in the bath!",
               "Oh god, they peed in the bath!",
               "You got water everywhere"
            };
         }
      },
      QUIT(5) {
         @Override
         public String[] applyChoice(Pet pet) {
            return {
               "Goodbye!"
            };
         }
      },

      public abstract String[] applyChoice(Pet pet);
   }

   public static void main(String[] args) {

      Scanner input = new Scanner(System.in);

      Pet pet = new Pet();

      int happiness = 5;
      int hunger = 5;
      int energy = 5;
      
      Choices choice = (Choices)1;
      
      System.out.println("Welcome to Virtual Pet Simulator!");
      System.out.print("Enter your pet name: ");
      
      pet.name = input.nextLine();
      
      System.out.println("Your pet's name is "+pet.name+"!");

      // TODO 1:
      // Create a while loop that continues while:
      // happiness < 15
      // hunger < 10
      // energy > 0
      // choice != 5
      while(pet.isOkay && choice != Choices.QUIT) {
         // TODO 2:
         // Display happiness, hunger, and energy.
         pet.displayAttr();
         
         System.out.println("What would you like to do?");
         for (choice : Choices.values())
            System.out.println((int)choice+". "+choice.name());
         
         choice = (Choices)input.nextInt();
         
         // TODO 3:
         // Create an if / else if / else statement
         // to handle the player's choice.
         //
         // Update the three statistics according
         // to the table in the instructions.
         //
         // Print a funny message for each action.

         String[] responces = choice.applyChoice(pet);

         // math random
      }

      // TODO 4:
      // After the loop, determine WHY the game ended.
      //
      // Did the pet become happy enough?
      // Did it get too hungry?
      // Did it run out of energy?
      // Did the player quit?
      //
      // Print the appropriate ending message.
      input.close();
   }
}