package Nomination.model;

public final class Trainee {

   private int id;
   private String name;
   private String company;
   private int age;
   private String rank;

   public void setId(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }

   public void setName(String name) {
	   this.name = name;
   }

	public String getName() {
	  return this.name;
	}

   public void setCompany(String company) {
	   this.company = company;
   }

   public String getCompany() {
	  return this.company;
	}

	
	public void setAge(int age) {
	   this.age = age;
	}

	public int getAge() {
	  return this.age;
	}
	
    public void setRank(String rank) {
      this.rank = rank;
    }

    public String getRank() {
      return this.rank;
    }
}