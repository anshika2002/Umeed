package list_ngo;

public class Contacto {
    private String num, cor, direction;
    private int img;

    public Contacto(String numero, String correo, String direccion, int img) {
        this.num = numero;
        this.cor = correo;
        this.direction = direccion;
        this.img = img;
    }

    public String getCor() {
        return cor;
    }

    public String getDirection() {
        return direction;
    }

    public String getNum() {
        return num;
    }


    public int getImg() {
        return img;
    }
}
