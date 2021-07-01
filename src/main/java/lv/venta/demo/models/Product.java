package lv.venta.demo.models;
// pēdējais koments 
// galīgi pēdējais final gala   
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//MODEL from MVC
@Table(name="ProductTable")
@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Product {

	//1. data part
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	@Setter(value=AccessLevel.NONE)
	private int id;
	
	
	@NotNull(message="Nedrīkst būt null vērtība")
	@NotEmpty(message="Nedrīkst būt tukšs")
	@Size(min=3, max = 20, message="Simbolu skaitam jābūt no 3 līdz 20")
	@Pattern(regexp="[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}[a-zāčēģīķļņšūž]+", message="Var saturēt tikai burtus")
	@Column(name="Category")
	private String category;
	
	@NotNull
	@NotEmpty
	@Size(min=3, max = 20)
	@Pattern(regexp="[A-ZĀČĒĢĪĶĻŅŠŪŽ]{1}[a-zāčēģīķļņšūž\\s]+")
	@Column(name="Title")
	private String title;
	
	@Min(0)
	@Max(1000)
	@Column(name="Price")
	private float price;//eur
	
	@Min(0)
	@Max(200)
	@Column(name="Weight")
	private double weight;//kg
	
	@Column(name="Description")
	private String description;
	
	@Min(0)
	@Max(1000)
	@Column(name="Quantity")
	private int quantity;
	
	//3. Contructors
		public Product(String category, String title, float price, double weight, String description, int quantity) {
			this.category = category;
			this.title = title;
			this.price = price;
			this.weight = weight;
			this.description = description;
			this.quantity = quantity;
			
			//this.id = idCounter;
			//idCounter++;
			
		}
	
	
	
}
