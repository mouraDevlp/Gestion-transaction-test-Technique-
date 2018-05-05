
public class Transaction  {//implements Comparable<Transaction>
	
	private String txtId;
	private String date;
	private String magasin;
	private String idProduit;
	private int quantite;
	private double prix;
	
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(String txtId, String date, String magasin, String idProduit, int quantite, double prix) {
		super();
		this.txtId = txtId;
		this.date = date;
		this.magasin = magasin;
		this.idProduit = idProduit;
		this.quantite = quantite;
		this.prix = prix;
	}
	
	
	
	
	public String getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(String idProduit) {
		this.idProduit = idProduit;
	}
	public String getTxtId() {
		return txtId;
	}
	public void setTxtId(String txtId) {
		this.txtId = txtId;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getMagasin() {
		return magasin;
	}
	public void setMagasin(String magasin) {
		this.magasin = magasin;
	}
	
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
//	@Override
//	public int compareTo(Transaction trans) {
//		// TODO Auto-generated method stub
//		 return (this.quantite - trans.quantite);
//	}
	 public String toString() {
	        return "[txtId=" + this.txtId + ", date=" + this.date + ", magasin=" +
	                this.magasin + ", idProduit=" + this.idProduit + ", quantite=" + this.quantite + ", prix=" + this.prix + ", " + (int) this.getPrix() * this.getQuantite() + "]";
	    }

}