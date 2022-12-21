package com.Telas.TelaGerar;

import com.Gerar.Gerar;
import com.ListaQuestoes.Questao.Aberta;
import com.ListaQuestoes.Questao.Objetiva;
import com.ListaQuestoes.Questao.Questao;
import com.ListaQuestoes.Questao.VouF;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class GerarDocx {


    List <Questao> listaProva;
    public GerarDocx(String instituicao, String nomeProfessor, List<Questao> listaProva) throws Exception {
        this.listaProva = listaProva;
        newWordDoc("Prova.docx", instituicao, nomeProfessor);

    }
    public void newWordDoc(String filename, String instituicao, String nomeProfessor)
    {
        XWPFDocument document = new XWPFDocument();


            String prova = "";
            int cont = 1;
        String cabecalho = "                                                                        Avaliação                                            \n\n\n                 Professor: "+nomeProfessor+"         \nInstituição de ensino: "+instituicao+"\n                                                                                                                             Aluno:____________________________________________" +
                " Matricula :________________"+"\nData:___/___/_______\n\n\n";

        XWPFParagraph cabecalhoo = document.createParagraph();
        XWPFRun cab = cabecalhoo.createRun();
        cab.setText(cabecalho);

            try {




                        for (int j = 0; j < listaProva.size(); j++) {

                            Questao q = listaProva.get(j);

                            XWPFParagraph tmpParagraph = document.createParagraph();
                            XWPFRun tmpRun = tmpParagraph.createRun();

                            prova = (cont++ + ") " + q.getPergunta());
                            tmpRun.setText(prova);

                            if (q.getTipo().equals("Objetiva")) {

                                q = (Objetiva) q;
                                for (int k = 0; k < ((Objetiva) q).getAlternativas().length; k++) {

                                    tmpParagraph = document.createParagraph();
                                    tmpRun = tmpParagraph.createRun();
                                    tmpRun.setText(("( "+ (char)(k + 97)) +" ) "+((Objetiva) q).getAlternativas()[j]);
                                }


                            }
                            else{
                                tmpParagraph = document.createParagraph();
                                tmpRun = tmpParagraph.createRun();
                                tmpRun.setText("__________________________________________________________________________________________________________________________________________________________");

                            }

                    }



                FileOutputStream fos = new FileOutputStream(new File(filename));
                document.write(fos);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            XWPFDocument gabarito = new XWPFDocument();

            XWPFParagraph w = gabarito.createParagraph();
            XWPFRun p = w.createRun();
            p.setText("Professor: "+nomeProfessor);
            for(int i = 0; i < listaProva.size(); i++){

                XWPFParagraph a = gabarito.createParagraph();
                XWPFRun b = a.createRun();
                b.setText((i+1)+"): ");
                if(listaProva.get(i).getTipo().equals("Aberta")){
                    b.setText(((Aberta)listaProva.get(i)).getResposta());
                }
                else if(listaProva.get(i).getTipo().equals("Objetiva")){
                    b.setText(((Objetiva)listaProva.get(i)).getCorretas());
                }
                else if(listaProva.get(i).getTipo().equals("VouF")){
                    if(((VouF)listaProva.get(i)).getResposta()){
                        b.setText("Verdadeiro");
                    }
                    else{
                        b.setText("Falso");
                    }
                }
            }

        try {
            FileOutputStream pp = new FileOutputStream(new File("Gabarito.docx"));
            gabarito.write(pp);
            pp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }
