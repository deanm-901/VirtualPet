import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Pet {
   public String name;
   public int happiness;
   public int hunger;
   public int energy;

   public Pet() {
      this.happiness = 5;
      this.hunger    = 5;
      this.energy    = 5;
   }

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

   public boolean isOkay() {
      return ((this.happiness < 15) && (this.hunger < 10) && (this.energy > 0));
   }

   public String getEndingMessage() {
      String s = "";

      if (this.happiness >= 15)
         s = this.name+" became so happy! Good Job!";
      
      if (this.hunger >= 10)
         s = this.name+" has died of hunger.";
      
      if (this.energy <= 0)
         s = this.name+" ran out of energy and died.";

      return s;
   }
}

public class VirtualPet {
   public enum Choices {
      FEED(1) {
         @Override
         public String[] applyChoice(Pet pet) {
            pet.feed();
            return new String[] {
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
            return new String[] {
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
            return new String[] {
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
            return new String[] {
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
            return new String[] {
               "Goodbye!"
            };
         }
      };

      public abstract String[] applyChoice(Pet pet);

      final int value;
      Choices(int value) {
         this.value = value;
      }
      public int getValue() {
         return value;
      }
      public static Choices fromValue(int value) {
         for (Choices choice: Choices.values()) {
            if (choice.getValue() == value) return choice;
         }
         System.out.println("Not a choice! You decide to feed them instead");
         return Choices.FEED;
      }
   }

   public static void main(String[] args) {

      Scanner input = new Scanner(System.in);

      Pet pet = new Pet();
      
      Choices choice = Choices.FEED;
      
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
      while(pet.isOkay() && choice.getValue() != Choices.QUIT.getValue()) {
         // TODO 2:
         // Display happiness, hunger, and energy.
         pet.displayAttr();
         
         System.out.println("What would you like to do?");
         for (Choices chc : Choices.values())
            System.out.println(chc.getValue()+". "+chc.name());
         
         choice = Choices.fromValue(input.nextInt());
         
         // TODO 3:
         // Create an if / else if / else statement
         // to handle the player's choice.
         //
         // Update the three statistics according
         // to the table in the instructions.
         //
         // Print a funny message for each action.

         String[] responces = choice.applyChoice(pet);

         String responce = responces[(int)(Math.random()*responces.length)];
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
      System.out.println(pet.getEndingMessage());

      input.close();
   }
}