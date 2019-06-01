package com.example.egovernment.Library;

public class PDF {
    private String pdf_name;
    private String image_url;
    private String pdf_url;

    public PDF(String pdf_name, String image_url, String pdf_url) {
        this.pdf_name = pdf_name;
        this.image_url = image_url;
        this.pdf_url = pdf_url;
    }
    public String getPdf_name() {
        return pdf_name;
    }

    public void setPdf_name(String pdf_name) {
        this.pdf_name = pdf_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }
}
