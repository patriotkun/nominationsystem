package Nomination.model;

public final class LoginUser {

   private String id;
   private String name;
   private String password;
   private int th;

   public void setId(String id) {
      this.id = id;
   }

   public String getId() {
      return this.id;
   }

   public void setName(String name) {
	   this.name = name;
   }

	public String getName() {
	  return this.name;
	}

   public void setPassword(String password) {
	   this.password = password;
   }

   public String getPassword() {
	  return this.password;
	}
   public void setTh(int th) {
	   this.th = th;
   }

   public int getTh() {
	  return this.th;
	}
}