package gov.goias.persistencia.corporativo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import gov.goias.io.Transacao;

/**
  	SISTEMA COORPORATIVO DO ESTADO DE GOIÁS - SCORP
	STI - Secretaria de Teconologia da Informação (SGG)
	Copyright (c) 2023
*/

public class PessoaFisica extends Pessoa {

    private Transacao objTransacao;

    /*
     * Variveis de gets
     */
    private String nome;
    private String nomeMae;
    private String nomePai;
    private java.util.Date dataNascimento;
    private String sexo;
    private long numrCPF;
    private String numrRG;
    private String orgaoRG;     
    private String ufRg;
    private java.util.Date dataExpedicaoRG;
    
    private java.util.Date dataExpedicaoCIN;
    private String orgaoCIN;
    private String codMunicipio;
    private String txtUF;
    private String txtNomeUF;
    private String nomeMunicipio;
    
    private long numrMatrBase;
    private String tipoSanguineo;
    private char tipoFatorRh;
    private char indicadorDoador;
    private char tipoEstadoCivil;
    private int tipoGrauInstrucao;
    private int grauInstrucao;
    private int codgMuniNatu;
    private int codgPaisNatu;
    private int paisNacionalidade;
    private int anoChegadaBrasil;
    private java.util.Date dataNaturalizacao;
    private long numrCNH;
    private String tipoCategCNH;
    private java.util.Date dataExpedCNH;
    private java.util.Date dataValidCNH;
    private String ufCnh;
    private String numrTitEleitor;
    private String zonaEleitor;
    private String secaoEleitor;
    private int codgMuniTitEleitor;
    private java.util.Date dataExpedTitEleitor;
    private char statServMilit;
    private String numrCertMilitar;
    private String siglForcaMilitar;
    private String codgRegiaoMilitar;
    private String numrPassaporte;
    private java.util.Date dataVencPassaporte;
    private int codgPaisPassaporte;
    private java.util.Date dataObito;
    private String matriculaCertObito;
    private String numrCertObito;
    private String cartorioCertObito;
    private String livroCertObito;
    private String folhaCertObito;
    private java.util.Date dataExpedCertObito;
    private int codgMunicObito;
    private String descCausaMortis;
    private String matriculaCertNasc;
    private String numrCertNasc;
    private String cartorioCertNasc;
    private String cartorioCertCasamento;
    private String livroCertNasc;
    private String folhaCertNasc;
    private java.util.Date dataExpedCertNasc;
    private int codgMuniCertNasc;
    private String cidadeEstadoEquivNasc;
    private int codgPaisCasamento;
    private int codgMuniCasamento;
    private String cidadeEstadoEquivCasamento;
    private String matriculaCertCasamento;
    private String numrCertCasamento;
    private String livroCertCasamento;
    private String folhaCertCasamento;
    private java.util.Date dataExpedCertCasamento;
    private String nomeConjuge;   

    /*
     * flags de alterao
     */
    private boolean altNumrCPF;
    private boolean altNome;
    private boolean altDataNascimento;
    private boolean altNumrRG;
    private boolean altNomeMae;
    private boolean altNomePai;
    private boolean altDataObito;
    private boolean altPaisNacionalidade;
    private boolean altDataNaturalizacao;
    private boolean altUFRG;
    private boolean altExpedicaoRG;
    private boolean altOrgaoRG;
    private boolean altNumrMatrBase;
    private boolean altSexo;
    private boolean altNumrCNH;
    private boolean altTipoCategCNH;
    private boolean altDataExpedCNH;
    private boolean altDataValidCNH;
    private boolean altUFCNH;
    private boolean altNumrTitEleitor;
    private boolean altZonaEleitor;
    private boolean altSecaoEleitor;
    private boolean altDataExpedTitEleitor;
    private boolean altStatServMilit;
    private boolean altNumrCertMilitar;
    private boolean altSiglForcaMilitar;
    private boolean altCodgRegiaoMilitar;
    private boolean altMatriculaCertObito;
    private boolean altNumrCertObito;
    private boolean altCartorioCertObito;
    private boolean altLivroCertObito;
    private boolean altFolhaCertObito;
    private boolean altDataExpedCertObito;
    private boolean altCodgMunicObito;
    private boolean altDescCausaMortis;
    private boolean altTipoEstadoCivil;
    private boolean altTipoGrauInstrucao;
    private boolean altGrauInstrucao;
    private boolean altCodgPaisNatu;
    private boolean altAnoChegadaBrasil;
    private boolean altCodgMuniNatu;
    private boolean altNumrPassaporte;
    private boolean altTipoSanguineo;
    private boolean altIndicadorDoador;
    private boolean altTipoFatorRh;
    private boolean altCodgMuniTitEleitor;
    private boolean altDataVencPassaporte;
    private boolean altCodgPaisPassaporte;
    private boolean altMatriculaCertNasc;
    private boolean altNumrCertNasc;
    private boolean altCartorioCertNasc;
    private boolean altCartorioCertCasamento;
    private boolean altLivroCertNasc;
    private boolean altFolhaCertNasc;
    private boolean altDataExpedCertNasc;
    private boolean altCodgMuniCertNasc;
    private boolean altCidadeEstadoEquivNasc;
    private boolean altPaisCasamento;
    private boolean altCodgMuniCasamento;
    private boolean altCidadeEstadoEquivCasamento;
    private boolean altMatriculaCertCasamento;
    private boolean altNumrCertCasamento;
    private boolean altLivroCertCasamento;
    private boolean altFolhaCertCasamento;
    private boolean altDataExpedCertCasamento;
    private boolean altNomeConjuge;
    private boolean altEmail;
    private boolean altEmailOficial;    
    private boolean altDataExpedicaoCIN;
    private boolean altOrgaoCIN;
    private boolean altCodMunicipio;    
    private boolean altTxtUF;
    private boolean altTxtNomeUF;
    private boolean altNomeMunicipio;
    
    /*
     * Constantes Publicas
     */
    public static final String FON_NOME = "N";
    public static final String FON_MAE = "M";
    /*
     * Variveis de trabalho
     */
    private String sql;
    private PreparedStatement pstmtMigracao;
    private ResultSet rset;
    private SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

    /*
     * Tratando Complemento
     */
    private boolean leuComplemento; // informa se o Complemento foi lido

    public PessoaFisica() {/*
         * Constructor
         */
        setObjTransacao(new Transacao("SCORP"));
    }

    public PessoaFisica(Transacao objTransacao) {/*
         * Constructor
         */
        super(objTransacao);
        this.setObjTransacao(objTransacao);
    }

    /**
     * Consulta uma pessoa fsica pelo identificador
     *
     * @param iden Identificador da pessoa fsica
     * @return true se OK, false se erro
     */
    public boolean consultar(long iden) {
        this.limparPropriedades();
        if (!this.consultarPessoa("pfis_iden = " + iden)) {
            return false;
        }
        return true;
    }

    /**
     * Consulta uma pessoa fsica pelo CPF e/ou nome
     *
     * @param cpf CPF da pessoa
     * @param nome Nome da pessoa
     * @return true se OK, false se erro
     */
    public boolean consultar(long cpf, String nome) {

        this.limparPropriedades();
        String compl = null;
        if (cpf != 0) {
            compl = "pfis_numr_cpf = " + cpf;
        }
        if (nome != null) {
            if (compl != null) {
                compl = compl + " and ";
            }
            compl = compl + "pfis_iden in (" + this.montarSqlFonetico(Pessoa.tratarNome(nome), FON_NOME) + ")";
        }
        if (!this.consultarPessoa(compl)) {
            return false;
        }

        return true;
    }

    /**
     * Consulta uma pessoa fsica pelo CPF
     *
     * @param cpf CPF
     * @return true se OK, false se erro
     */
    public boolean consultarCPF(long cpf) {
        this.limparPropriedades();
        if (!this.consultarPessoa("PFIS_NUMR_CPF = " + cpf)) {
            return false;
        }

        return true;
    }

    /**
     * Consulta uma pessoa fsica pelo nmero do Registro Geral
     *
     * @param numrRG nmero do Registro Geral
     * @return true se OK, false se erro
     */
    public boolean consultarIdentidade(String numrRG) {
        this.limparPropriedades();
        if (!this.consultarPessoa("PFIS_NUMR_RG = '" + numrRG + "'")) {
            return false;
        }

        return true;
    }

    public boolean consultarIdentidadeNomeMae(String numrRG, String nomeMae) {
        this.limparPropriedades();

        if (!this.consultarPessoa("PFIS_NUMR_RG = '" + numrRG + "' AND PFIS_NOME_MAE LIKE '" + nomeMae.toUpperCase() + "%'")) {
            return false;
        }

        return true;
    }

    public boolean consultarIdentidadeDataNascimento(String numrRG, String dataNascimento) {
        this.limparPropriedades();

        if (!this.consultarPessoa("PFIS_NUMR_RG = '" + numrRG + "' AND PFIS_DATA_NASC = TO_DATE('" + dataNascimento + "', 'DD/MM/YYYY')")) {
            return false;
        }

        return true;
    }

    public boolean consultarIdentidadeNomeMaeDataNascimento(String numrRG, String nomeMae, String dataNascimento) {
        this.limparPropriedades();

        if (!this.consultarPessoa("PFIS_NUMR_RG = '" + numrRG + " AND PFIS_NOME_MAE LIKE '" + nomeMae.toUpperCase() + "%' AND PFIS_DATA_NASC = TO_DATE('" + dataNascimento + "', 'DD/MM/YYYY')")) {
            return false;
        }

        return true;
    }

    /**
     * Informa que est iniciando um processo de Incluso
     */
    public final void iniciarIncluir() {
        this.limparPropriedades();
        emInclusao = true;
        this.iniciarTratarComum();

    }

    /**
     * Informa que est iniciando um processo de Alterao
     */
    public final void iniciarAlterar() {
        this.limparIndAlteracao();
        emAlteracao = true;
        this.iniciarTratarComum();
    }

    /**
     * Incluir os dados da pessoa Retorna um valor booleano indicando se a
     * incluso foi efetuada com sucesso. <p> Para executar este mtodo 
     * obrigatrio setar os seguintes mtodos: <p> setNome() -- seta o nome da
     * pessoa <p> Podendo tambm ser setados outros mtodos mas no
     * obrigatrios: <p> setCPF() -- seta o CPF da pessoa <p>
     * setDataNascimento() -- seta a data de nascimento da pessoa <p>
     * setnumrRG() -- seta o nmero do RG da pessoa <p> setNomeMae() -- seta o
     * nome da me da pessoa <p> setNomePai() -- seta o nome do pai da pessoa
     * <p> setPaisNacionalidade() -- seta o pas de nacionalidade da pessoa <p>
     * setUFRG() -- seta a UF do RG da pessoa <p> setDataExpedicaoRG() -- seta a
     * data de expedio do RG da pessoa <p> setOrgaoExpedicaoRG() -- seta o
     * rgo expedidor do RG da pessoa <p> setNumrMatrBase() -- seta o nmero da
     * matrcula base da pessoa <p> settIndicadorDoador() -- seta o indicador
     * doador da pessoa <p> setFatorRH() -- seta o fator RH da pessoa <p>
     * setTipoSanguineo() -- seta o tipo sanguneo da pessoa <p> setSexo() --
     * seta o sexo da pessoa <p> setTipoEstadoCivil() -- seta o tipo do estado
     * civil da pessoa <p> setTipoGrauInstrucao() -- seta o tipo do grau de
     * instruo da pessoa <p> setGrauInstrucao() -- seta o grau de instruo da
     * pessoa <p> setCodgMuniNatu() -- seta o cdigo do municpio de
     * naturalidade da pessoa <p> setCodgPaisNatu() -- seta o cdigo do pas de
     * naturalidade da pessoa <p> setAnoChegadaBrasil() -- seta o ano de chegada
     * ao Brasil
     * <p>
     * setDataNaturalizacao() -- seta a data de Naturalizao
     *
     * @return <i>true</i> se a incluso realizada com sucesso, e <i>false</i>
     * se houve algum erro. Quando o retorno for <i>false</i> os seguintes
     * mtodos podero ser usados:<p> <li>getErroMensagem() - mensagem que
     * especifica o problema encontrado.</li> <li>getErroCodigo() - cdigo do
     * problema encontrado.</li></p> Quando o retorno for <i>true</i> os
     * seguintes mtodos podero ser usados: <p> getNome() -- retorna o nome da
     * pessoa - tipo: String <p> getCPF() -- retorna o CPF da pessoa - tipo:
     * long <p> getDataNascimento() -- retorna a data de nascimento da pessoa -
     * tipo: java.util.Date <p> getNumrRG() -- retorna o nmero do RG da pessoa
     * - tipo: String <p> getNomeMae() -- retorna o nome da me da pessoa -
     * tipo: String <p> getNomePai() -- retorna o nome do pai da pessoa - tipo:
     * String <p> getPaisNacionalidade() -- retorna o pas de nacionalidade da
     * pessoa - tipo: int <p> getUFRG() -- retorna a UF do RG da pessoa - tipo:
     * String <p> getDataExpedicaoRG() -- retorna a data de expedio do RG da
     * pessoa - tipo: java.util.Date <p> getOrgaoExpedicaoRG() -- retorna o
     * rgo expedidor do RG da pessoa - tipo: String <p> getNumrMatrBase() --
     * retorna o nmero da matrcula base da pessoa - tipo: long <p>
     * getIndicadorDoador() -- retorna o indicador doador da pessoa - tipo:
     * String <p> getFatorRH() -- retorna o fator RH da pessoa - tipo: char <p>
     * getTipoSanguineo() -- retorna o tipo sanguneo da pessoa - tipo: String
     * <p> getSexo() -- retorna o sexo da pessoa - tipo: String <p>
     * getTipoEstadoCivil() -- retorna o tipo do estado civil da pessoa - tipo:
     * char <p> getTipoGrauInstrucao() -- retorna o tipo do grau de instruo da
     * pessoa - tipo: int <p> getGrauInstrucao() -- retorna o grau de instruo
     * da pessoa - tipo: int <p> getCodgMuniNatu() -- retorna o cdigo do
     * municpio de naturalidade da pessoa - tipo: int <p> getCodgPaisNatu() --
     * retorna o cdigo do pas de naturalidade da pessoa - tipo: int <p>
     * getAnoChegadaBrasil() -- retorna o ano de chegada ao Brasil - tipo: int
     * <p>
     * getDataNaturalizacao() -- retorna a data de Naturalizao - tipo: Date
     */
    public final boolean incluir() {
        long prox = this.proximoID();
        if (prox == 0) { // erro de obteno de prximo
            erroMensagem = "Erro ao obter prximo:" + erroMensagem;
            erroCodigo = getObjTransacao().getErroCodigo();
            return false;
        }

        this.iden = 0;
        sql = "INSERT INTO PESSOAS_FISICAS (PFIS_IDEN, "
                + "PFIS_NOME, "
                + "PFIS_NOME_MAE, "
                + "PFIS_NOME_PAI, "
                + "PFIS_DATA_NASC, "
                + "PFIS_SIGL_SEXO, "
                + "PFIS_NUMR_CPF, "
                + "PFIS_NUMR_RG, "
                + "PFIS_DATA_EXPED_RG, "
                + "PFIS_SIGL_ORGAO_RG, "
                + "PFIS_UFED_SIGL_RG, "
                + "PFIS_NUMR_MATRICULA_BASE, "
                + "PFIS_TIPO_SANGUINEO, "
                + "PFIS_TIPO_FATOR_RH, "
                + "PFIS_INDI_DOADOR, "
                + "PFIS_PAIS_CODG_NACIONALIDADE, "
                + "PFIS_TIPO_ESTADO_CIVIL, "
                + "PFIS_TIPO_GRAU_INSTRUCAO, "
                + "PFIS_GINS_CODG, "
                + "PFIS_MUNI_CODG_NATURALIDADE, "
                + "PFIS_PAIS_CODG_NASCIMENTO, "
                + "PFIS_NUMR_ANO_CHEGADA_BRASIL, "
                + "PFIS_DATA_NATURALIZACAO, "
                + "PFIS_SIGL_ORGAO_CIN, "
                + "PFIS_DATA_EMISSAO_CIN, "
                + "PFIS_MUNI_CODG_EMISSAO_CIN) VALUES ("
                + prox
                + ", " + tratarNulo(this.nome)
                + ", " + tratarNulo(this.nomeMae)
                + ", " + tratarNulo(this.nomePai)
                + ", " + tratarNulo(this.dataNascimento)
                + ", " + tratarNulo(this.sexo)
                + ", " + tratarNulo(this.numrCPF)
                + ", " + tratarNulo(this.numrRG)
                + ", " + tratarNulo(this.dataExpedicaoRG)
                + ", " + tratarNulo(this.orgaoRG)
                + ", " + tratarNulo(this.ufRg)
                + ", " + tratarNulo(this.numrMatrBase)
                + ", " + tratarNulo(this.tipoSanguineo)
                + ", " + tratarNulo(this.tipoFatorRh == '\u0000' ? null : String.valueOf(this.tipoFatorRh))
                + ", " + tratarNulo(this.indicadorDoador == '\u0000' ? null : String.valueOf(this.indicadorDoador))
                + ", " + tratarNulo(this.paisNacionalidade)
                + ", " + tratarNulo(this.tipoEstadoCivil == '\u0000' ? null : String.valueOf(this.tipoEstadoCivil))
                + ", " + tratarNulo(this.tipoGrauInstrucao)
                + " ," + tratarNulo(this.grauInstrucao)
                + ", " + tratarNulo(this.codgMuniNatu)
                + ", " + tratarNulo(this.codgPaisNatu)
                + ", " + tratarNulo(this.anoChegadaBrasil)
                + ", " + tratarNulo(this.dataNaturalizacao)
                + ", " + tratarNulo(this.orgaoCIN)
                + ", " + tratarNulo(this.dataExpedicaoCIN)
                + ", " + tratarNulo(this.codMunicipio)
                + ")";

        getObjTransacao().autenticarUsuarioPortal();
        if (getObjTransacao().executarSQL(sql) == 0) {
            erroMensagem = "iden:" + prox + ":" + getObjTransacao().getErroMensagem() + sql;
            erroCodigo = getObjTransacao().getErroCodigo();
            return false;
        }
        this.iden = prox;

        if (!this.incluirFonetica(FON_NOME, this.nome)) {
            this.iden = 0;
            return false;
        }

        if (!this.incluirFonetica(FON_MAE, this.nomeMae)) {
            this.iden = 0;
            return false;
        }

        if (this.altDataObito
                || this.altNumrCNH
                || this.altTipoCategCNH
                || this.altDataExpedCNH
                || this.altDataValidCNH
                || this.altUFCNH
                || this.altNumrTitEleitor
                || this.altZonaEleitor
                || this.altSecaoEleitor
                || this.altDataExpedTitEleitor
                || this.altCodgMuniTitEleitor
                || this.altStatServMilit
                || this.altNumrCertMilitar
                || this.altSiglForcaMilitar
                || this.altCodgRegiaoMilitar
                || this.altNumrPassaporte
                || this.altDataVencPassaporte
                || this.altCodgPaisPassaporte
                || this.altMatriculaCertObito
                || this.altNumrCertObito
                || this.altCartorioCertObito
                || this.altLivroCertObito
                || this.altFolhaCertObito
                || this.altDataExpedCertObito
                || this.altCodgMunicObito
                || this.altDescCausaMortis
                || this.altCartorioCertCasamento
                || this.altMatriculaCertCasamento
                || this.altNumrCertNasc
                || this.altMatriculaCertNasc
                || this.altCartorioCertNasc
                || this.altLivroCertNasc
                || this.altFolhaCertNasc
                || this.altDataExpedCertNasc
                || this.altCodgMuniCertNasc
                || this.altDataNaturalizacao
                || this.altDataExpedicaoCIN
                || this.altOrgaoCIN
                || this.altCodMunicipio) {

            if (!this.incluirComplemento()) {
                this.iden = 0;
                return false;
            }
        }
        if (!this.tratarComum()) {
            this.iden = 0;
            return false;
        }
        return true;
    }

    /**
     * Alterar os dados da pessoa Retorna um valor booleano indicando se a
     * alterao foi efetuada com sucesso. <p> Para executar este mtodo 
     * obrigatrio setar os seguintes mtodos: <p> setNome() -- seta o nome da
     * pessoa <p> setId() -- seta o iden da pessoa <p> Podendo tambm ser
     * setados outros mtodos mas no obrigatrios: <p> setCPF() -- seta o CPF
     * da pessoa <p> setDataNascimento() -- seta a data de nascimento da pessoa
     * <p> setnumrRG() -- seta o nmero do RG da pessoa <p> setNomeMae() -- seta
     * o nome da me da pessoa <p> setNomePai() -- seta o nome do pai da pessoa
     * <p> setPaisNacionalidade() -- seta o pas de nacionalidade da pessoa <p>
     * setUFRG() -- seta a UF do RG da pessoa <p> setDataExpedicaoRG() -- seta a
     * data de expedio do RG da pessoa <p> setOrgaoExpedicaoRG() -- seta o
     * rgo expedidor do RG da pessoa <p> setNumrMatrBase() -- seta o nmero da
     * matrcula base da pessoa <p> settIndicadorDoador() -- seta o indicador
     * doador da pessoa <p> setFatorRH() -- seta o fator RH da pessoa <p>
     * setTipoSanguineo() -- seta o tipo sanguneo da pessoa <p> setSexo() --
     * seta o sexo da pessoa <p> setTipoEstadoCivil() -- seta o tipo do estado
     * civil da pessoa <p> setTipoGrauInstrucao() -- seta o tipo do grau de
     * instruo da pessoa <p> setGrauInstrucao() -- seta o grau de instruo da
     * pessoa <p> setCodgMuniNatu() -- seta o cdigo do municpio de
     * naturalidade da pessoa <p> setCodgPaisNatu() -- seta o cdigo do pas de
     * naturalidade da pessoa <p> setAnoChegadaBrasil() -- seta o ano de chegada
     * ao Brasil
     * <p>
     * setDataNaturalizacao() -- seta a data de Naturalizao
     *
     * @return <i>true</i> se a alterao realizada com sucesso, e <i>false</i>
     * se houve algum erro. Quando o retorno for <i>false</i> os seguintes
     * mtodos podero ser usados:<p> <li>getErroMensagem() - mensagem que
     * especifica o problema encontrado.</li> <li>getErroCodigo() - cdigo do
     * problema encontrado.</li></p> Quando o retorno for <i>true</i> os
     * seguintes mtodos podero ser usados: <p> getNome() -- retorna o nome da
     * pessoa - tipo: String <p> getCPF() -- retorna o CPF da pessoa - tipo:
     * long <p> getDataNascimento() -- retorna a data de nascimento da pessoa -
     * tipo: java.util.Date <p> getNumrRG() -- retorna o nmero do RG da pessoa
     * - tipo: String <p> getNomeMae() -- retorna o nome da me da pessoa -
     * tipo: String <p> getNomePai() -- retorna o nome do pai da pessoa - tipo:
     * String <p> getPaisNacionalidade() -- retorna o pas de nacionalidade da
     * pessoa - tipo: int <p> getUFRG() -- retorna a UF do RG da pessoa - tipo:
     * String <p> getDataExpedicaoRG() -- retorna a data de expedio do RG da
     * pessoa - tipo: java.util.Date <p> getOrgaoExpedicaoRG() -- retorna o
     * rgo expedidor do RG da pessoa - tipo: String <p> getNumrMatrBase() --
     * retorna o nmero da matrcula base da pessoa - tipo: long <p>
     * getIndicadorDoador() -- retorna o indicador doador da pessoa - tipo:
     * String <p> getFatorRH() -- retorna o fator RH da pessoa - tipo: char <p>
     * getTipoSanguineo() -- retorna o tipo sanguneo da pessoa - tipo: String
     * <p> getSexo() -- retorna o sexo da pessoa - tipo: String <p>
     * getTipoEstadoCivil() -- retorna o tipo do estado civil da pessoa - tipo:
     * char <p> getTipoGrauInstrucao() -- retorna o tipo do grau de instruo da
     * pessoa - tipo: int <p> getGrauInstrucao() -- retorna o grau de instruo
     * da pessoa - tipo: int <p> getCodgMuniNatu() -- retorna o cdigo do
     * municpio de naturalidade da pessoa - tipo: int <p> getCodgPaisNatu() --
     * retorna o cdigo do pas de naturalidade da pessoa - tipo: int <p>
     * getAnoChegadaBrasil() -- retorna o ano de chegada ao Brasil - tipo: int
     * <p>
     * getDataNaturalizacao() -- retorna a data de Naturalizao - tipo: Date
     */
    public final boolean alterar() {
        String strConector = "SET ";
        sql = "";
        if (altNome) {
            sql = sql + strConector + "PFIS_NOME = " + tratarNulo(this.nome);
            strConector = ",";
        }
        if (altNomeMae) {
            sql = sql + strConector + "PFIS_NOME_MAE = " + tratarNulo(this.nomeMae);
            strConector = ",";
        }
        if (altNomePai) {
            sql = sql + strConector + "PFIS_NOME_PAI = " + tratarNulo(this.nomePai);
            strConector = ",";
        }
        if (altDataNascimento) {
            sql = sql + strConector + "PFIS_DATA_NASC = " + tratarNulo(this.dataNascimento);
            strConector = ",";
        }
        if (altSexo) {
            sql = sql + strConector + "PFIS_SIGL_SEXO = " + tratarNulo(this.sexo);
            strConector = ",";
        }
        if (altNumrCPF) {
            sql = sql + strConector + "PFIS_NUMR_CPF = " + tratarNulo(this.numrCPF);
            strConector = ",";
        }
        if (altNumrRG) {
            sql = sql + strConector + "PFIS_NUMR_RG = " + tratarNulo(this.numrRG);
            strConector = ",";
        }
        if (altExpedicaoRG) {
            sql = sql + strConector + "PFIS_DATA_EXPED_RG = " + tratarNulo(this.dataExpedicaoRG);
            strConector = ",";
        }
        if (altOrgaoRG) {
            sql = sql + strConector + "PFIS_SIGL_ORGAO_RG = " + tratarNulo(this.orgaoRG);
            strConector = ",";
        }
        if (altUFRG) {
            sql = sql + strConector + "PFIS_UFED_SIGL_RG = " + tratarNulo(this.ufRg);
            strConector = ",";
        }
        if (altNumrMatrBase) {
            sql = sql + strConector + "PFIS_NUMR_MATRICULA_BASE = " + tratarNulo(this.numrMatrBase);
            strConector = ",";
        }
        if (altTipoSanguineo) {
            sql = sql + strConector + "PFIS_TIPO_SANGUINEO = " + tratarNulo(this.tipoSanguineo);
            strConector = ",";
        }
        if (altTipoFatorRh) {
            sql = sql + strConector + "PFIS_TIPO_FATOR_RH = " + tratarNulo(this.tipoFatorRh == '\u0000' ? null : String.valueOf(this.tipoFatorRh));
            strConector = ",";
        }
        if (altIndicadorDoador) {
            sql = sql + strConector + "PFIS_INDI_DOADOR = " + tratarNulo(this.indicadorDoador == '\u0000' ? null : String.valueOf(this.indicadorDoador));
            strConector = ",";
        }
        if (altPaisNacionalidade) {
            sql = sql + strConector + "PFIS_PAIS_CODG_NACIONALIDADE = " + tratarNulo(this.paisNacionalidade);
            strConector = ",";
        }
        if (altTipoEstadoCivil) {
            sql = sql + strConector + "PFIS_TIPO_ESTADO_CIVIL = " + tratarNulo(this.tipoEstadoCivil == '\u0000' ? null : String.valueOf(this.tipoEstadoCivil));
            strConector = ",";
        }
        if (altTipoGrauInstrucao) {
            sql = sql + strConector + "PFIS_TIPO_GRAU_INSTRUCAO = " + tratarNulo(this.tipoGrauInstrucao);
            strConector = ",";
        }
        if (altGrauInstrucao) {
            sql = sql + strConector + "PFIS_GINS_CODG = " + tratarNulo(this.grauInstrucao);
            strConector = ",";
        }
        if (altCodgMuniNatu) {
            sql = sql + strConector + "PFIS_MUNI_CODG_NATURALIDADE = " + tratarNulo(this.codgMuniNatu);
            strConector = ",";
        }
        if (altCodgPaisNatu) {
            sql = sql + strConector + "PFIS_PAIS_CODG_NASCIMENTO = " + tratarNulo(this.codgPaisNatu);
            strConector = ",";
        }
        if (altAnoChegadaBrasil) {
            sql = sql + strConector + "PFIS_NUMR_ANO_CHEGADA_BRASIL = " + tratarNulo(this.anoChegadaBrasil);
            strConector = ",";
        }
        if (altDataNaturalizacao) {
            sql = sql + strConector + "PFIS_DATA_NATURALIZACAO = " + tratarNulo(this.dataNaturalizacao);
            strConector = ",";
        }
        if (altDataExpedicaoCIN) {
            sql = sql + strConector + "PFIS_DATA_EMISSAO_CIN = " + tratarNulo(this.dataExpedicaoCIN);
            strConector = ",";
        }
        if (altOrgaoCIN) {
            sql = sql + strConector + "PFIS_SIGL_ORGAO_CIN = " + tratarNulo(this.orgaoCIN);
            strConector = ",";
        }
        if (altCodMunicipio) {
            sql = sql + strConector + "PFIS_MUNI_CODG_EMISSAO_CIN = " + tratarNulo(this.codMunicipio);
            strConector = ",";
        }
        
        try {
            if (!sql.equals("")) { // alterou pessoas

                sql = "UPDATE PESSOAS_FISICAS " + sql + " WHERE PFIS_IDEN = " + this.iden;
                getObjTransacao().autenticarUsuarioPortal();
                if (getObjTransacao().executarSQL(sql) != 1) {
                    erroMensagem = getObjTransacao().getErroMensagem();
                    erroCodigo = getObjTransacao().getErroCodigo();
                    return false;
                }
            }

            if (altNome && !this.incluirFonetica(FON_NOME, this.nome)) {
                this.limparPropriedades();
                return false;
            }

            if (altNomeMae && !this.incluirFonetica(FON_MAE, this.nomeMae)) {
                this.limparPropriedades();
                return false;
            }

            if (this.altDataObito
                    || this.altNumrCNH
                    || this.altTipoCategCNH
                    || this.altDataExpedCNH
                    || this.altDataValidCNH
                    || this.altUFCNH
                    || this.altNumrTitEleitor
                    || this.altZonaEleitor
                    || this.altSecaoEleitor
                    || this.altDataExpedTitEleitor
                    || this.altCodgMuniTitEleitor
                    || this.altStatServMilit
                    || this.altNumrCertMilitar
                    || this.altSiglForcaMilitar
                    || this.altCodgRegiaoMilitar
                    || this.altNumrPassaporte
                    || this.altDataVencPassaporte
                    || this.altCodgPaisPassaporte
                    || this.altMatriculaCertObito
                    || this.altCartorioCertCasamento
                    || this.altNumrCertObito
                    || this.altCartorioCertObito
                    || this.altLivroCertObito
                    || this.altFolhaCertObito
                    || this.altDataExpedCertObito
                    || this.altCodgMunicObito
                    || this.altDescCausaMortis
                    || this.altMatriculaCertNasc
                    || this.altNumrCertNasc
                    || this.altCartorioCertNasc
                    || this.altLivroCertNasc
                    || this.altFolhaCertNasc
                    || this.altDataExpedCertNasc
                    || this.altCodgMuniCertNasc
                    || this.altMatriculaCertCasamento
                    || this.altDataNaturalizacao) {

                if (this.consultarComplemento(false)) {
                    if (!this.alterarComplemento()) {
                        this.limparPropriedades();
                        return false;
                    }
                } else {
                    if (!this.incluirComplemento()) {
                        this.limparPropriedades();
                        return false;
                    }
                }
            }
            return this.tratarComum();

        } catch (Exception e) {
            erroMensagem = e.getMessage() + " - " + getObjTransacao().getErroMensagem();
            erroCodigo = getObjTransacao().getErroCodigo();
            getObjTransacao().cancelarTransacao();
            return false;
        }
    }

    /**
     * Excluir Pessoas_Fisicas Retorna um valor booleano indicando se a
     * alterado foi efetuada com sucesso. <p> Para executar este método 
     * obrigatrio setar os seguintes mtodos: <p> setId() -- seta o iden da
     * pessoa <p> setNome() -- seta o nome da pessoa
     *
     * @return <i>true</i> se a excluso realizada com sucesso, e <i>false</i>
     * se houve algum erro. Quando o retorno for <i>false</i> os seguintes
     * mtodos podero ser usados:<p> <li>getErroMensagem() - mensagem que
     * especifica o problema encontrado.</li> <li>getErroCodigo() - código do
     * problema encontrado.</li></p>
     */
    public final boolean excluir() {

        try {
            if (!getObjTransacao().getTransacaoEManual()) {
                erroMensagem = "A transao deve ser manual. ";
                erroCodigo = 1;
                return false;
            }

            if (!this.excluirFonetica(FON_NOME)) {
                return false;
            }

            if (!this.excluirFonetica(FON_MAE)) {
                return false;
            }

            if (this.consultarComplemento(false) && !this.excluirComplemento()) {
                return false;
            }

            sql = "DELETE PESSOAS_FISICAS WHERE PFIS_IDEN = ? AND PFIS_NOME = ?";

            getObjTransacao().autenticarUsuarioPortal();
            PreparedStatement pstmt = getObjTransacao().prepararSQL(sql);
            if (pstmt == null) {
                erroCodigo = getObjTransacao().getErroCodigo();
                erroMensagem = getObjTransacao().getErroMensagem();
                return false;
            }

            pstmt.setLong(1, this.iden);
            pstmt.setString(2, this.nome);

            if (getObjTransacao().executarSQL(pstmt) != 1) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return false;
            }

            return true;

        } catch (Exception e) {
            erroMensagem = "Erro ao excluir Pessoa Fisica. " + e.getMessage();
            erroCodigo = 1;
            return false;
        }
    }

    /**
     * Incluir os dados complementares da pessoa Retorna um valor booleano
     * indicando se a incluso foi efetuada com sucesso. <p> Para executar este
     * mtodo  obrigatrio setar os seguintes mtodos: <p> setId() -- seta o
     * iden da pessoa <p> Podendo tambm ser setados outros mtodos mas no
     * obrigatrios: <p> setDataObito() -- seta a data de bito da pessoa <p>
     * setNumrCNH() -- seta o nmero da CNH da pessoa <p> setTipoCategCNH() --
     * seta o tipo da categoria da CNH da pessoa <p> setDataExpedCNH() -- seta a
     * data de expedio da CNH da pessoa <p> setDataValidCNH() -- seta a data
     * de validade da CNH da pessoa <p> setUFCNH() -- seta a UF da CNH da pessoa
     * <p> setNumrTitEleitor() -- seta o nmero do ttulo de eleitor da pessoa
     * <p> setCodgMuniTitEleitor() -- seta o codigo do municpio do titulo de
     * eleitor da pessoa <p> setZonaEleitor() -- seta a zona eleitoral da pessoa
     * <p> setSecaoEleitor() -- seta a seo eleitoral da pessoa <p>
     * setDataExpedTitEleitor() -- seta a data de expedio do ttulo de eleitor
     * da pessoa <p> setStatServMilit() -- seta o status do servio militar da
     * pessoa <p> setNumrCertMilitar() -- seta o nmero do certificado militar
     * da pessoa <p> setSiglForcaMilitar() -- seta a sigla da fora militar da
     * pessoa <p> setCodgRegiaoMilitar() -- seta o cdigo da regio militar da
     * pessoa <p> setNumrPassaporte() -- seta o nmero do passaporte da pessoa
     * <p> setDataVencPassaporte() -- seta a data de vencimento do passaporte da
     * pessoa <p> setPaisPassaporte() -- seta o cdigo do pais do passaporte <p>
     * setNumrCertObito() -- seta o nmero da certido de bito da pessoa <p>
     * setCartorioCertObito() -- seta o cartorio da certido de bito da pessoa
     * <p> setLivroCertObito() -- seta o livro da certido de bito da pessoa
     * <p> setFolhaCertObito() -- seta a folha da certido de bito da pessoa
     * <p> setDataExpedCertObito() -- seta a data da expedio da certido de
     * bito da pessoa <p> setCodgMunicObito() -- seta o cdigo do municpio de
     * bito da pessoa <p> setDescCausaMortis() -- seta a descrio da causa
     * mortis
     *
     * @return <i>true</i> se a incluso realizada com sucesso, e <i>false</i>
     * se houve algum erro. Quando o retorno for <i>false</i> os seguintes
     * mtodos podero ser usados:<p> <li>getErroMensagem() - mensagem que
     * especifica o problema encontrado.</li> <li>getErroCodigo() - cdigo do
     * problema encontrado.</li></p> Quando o retorno for <i>true</i> os
     * seguintes mtodos podero ser usados: <p> getDataObito() -- retorna a
     * data de bito da pessoa - tipo: java.util.Date <p> getNumrCNH() --
     * retorna o nmero da CNH da pessoa - tipo: long <p> getTipoCategCNH() --
     * retorna o tipo da categoria da CNH da pessoa - tipo: String <p>
     * getDataExpedCNH() -- retorna a data de expedio da CNH da pessoa - tipo:
     * java.util.Date <p> getDataValidCNH() -- retorna a data de validade da CNH
     * da pessoa - tipo: java.util.Date <p> getUFCNH() -- retorna a UF da CNH da
     * pessoa - tipo: String <p> getNumrTitEleitor() -- retorna o nmero do
     * ttulo de eleitor da pessoa - tipo: String <p> getZonaEleitor() --
     * retorna a zona eleitoral da pessoa - tipo: String <p> getSecaoEleitor()
     * -- retorna a seo eleitoral da pessoa - tipo: String <p>
     * getDataExpedTitEleitor() -- retorna a data de expedio do ttulo de
     * eleitor da pessoa - tipo: java.util.Date <p> getCodgMuniTitEleitor() --
     * retorna o codigo do municpio do titulo de eleitor da pessoa - tipo: int
     * <p> getStatServMilit() -- retorna o status do servio militar da pessoa -
     * tipo: char <p> getNumrCertMilitar() -- retorna o nmero do certificado
     * militar da pessoa - tipo: String <p> getSiglForcaMilitar() -- retorna a
     * sigla da fora militar da pessoa - tipo: String <p>
     * getCodgRegiaoMilitar() -- retorna o cdigo da regio militar da pessoa -
     * tipo: String <p> getNumrPassaporte() -- retorna o nmero do passaporte da
     * pessoa - tipo: String <p> getDataVencPassaporte() -- retorna a data de
     * vencimento do passaporte da pessoa - tipo: java.util.Date <p>
     * getPaisPassaporte() -- retorna o cdigo do pais do passaporte - tipo: int
     * <p> getNumrCertObito() -- retorna o nmero da certido de bito da pessoa
     * - tipo: String <p> getCartorioCertObito() -- retorna o cartorio da
     * certido de bito da pessoa - tipo: String <p> getLivroCertObito() --
     * retorna o livro da certido de bito da pessoa - tipo: String <p>
     * getFolhaCertObito() -- retorna a folha da certido de bito da pessoa -
     * tipo: String <p> getDataExpedCertObito() -- retorna a data da expedio
     * da certido de bito da pessoa - tipo: java.util.Date <p>
     * getCodgMunicObito() -- retorna o cdigo do municpio de bito da pessoa -
     * tipo: int <p> getDescCausaMortis() -- retorna a descrio da causa mortis
     * - tipo: String <p> getNumrCertNasc() -- retorna o nmero da certido de
     * nascimento - tipo: String <p> getCartorioCertNasc() -- retorna o nmero
     * do cartrio da certido de nascimento - tipo: String <p>
     * getLivroCertNasc() -- retorna o livro da certido de nascimento - tipo:
     * String <p> getFolhaCertNasc() -- retorna a folha da certido de
     * nascimento - tipo: String <p> getDataExpedCertNasc() -- retorna a data de
     * expedio da certido de nascimento - tipo: java.util.Date <p>
     * getCodgMuniCertNasc() -- retorna o cdigo do municpio da certido de
     * nascimento - tipo: int
     */
    private boolean incluirComplemento() {

        sql = "INSERT INTO PESSOAS_FISICAS_COMPLEMENTO("
                + "PFIC_PFIS_IDEN, "
                + "PFIC_DATA_OBITO, "
                + "PFIC_NUMR_CART_MOTORISTA, "
                + "PFIC_TIPO_CATEG_CART_MOTORISTA, "
                + "PFIC_DATA_EXPED_CART_MOTORISTA, "
                + "PFIC_DATA_VALID_CART_MOTORISTA, "
                + "PFIC_UFED_SIGL_CART_MOTORISTA, "
                + "PFIC_NUMR_TIT_ELEITOR, "
                + "PFIC_INFO_ZONA_TIT_ELEITOR, "
                + "PFIC_INFO_SECAO_TIT_ELEITOR, "
                + "PFIC_DATA_EXPED_TIT_ELEITOR, "
                + "PFIC_MUNI_CODG_TIT_ELEITOR, "
                + "PFIC_STAT_SERV_MILITAR, "
                + "PFIC_NUMR_CERT_SERV_MILITAR, "
                + "PFIC_SIGL_FORCA_SERV_MILITAR, "
                + "PFIC_CODG_REGIAO_SERV_MILITAR, "
                + "PFIC_NUMR_PASSAPORTE, "
                + "PFIC_DATA_VENC_PASSAPORTE, "
                + "PFIC_PAIS_CODG_PASSAPORTE, "
                + "PFIC_NUMR_MATRICULA_CERT_OBITO, "
                + "PFIC_NUMR_CERT_OBITO, "
                + "PFIC_INFO_CARTORIO_CERT_OBITO, "
                + "PFIC_INFO_LIVRO_CERT_OBITO, "
                + "PFIC_INFO_FOLHA_CERT_OBITO, "
                + "PFIC_DATA_EXPED_CERT_OBITO, "
                + "PFIC_MUNI_CODG_OBITO, "
                + "PFIC_DESC_CAUSA_MORTIS, "
                + "PFIC_NUMR_MATRICULA_CERT_NASC, "
                + "PFIC_NUMR_CERT_NASC, "
                + "PFIC_INFO_CARTORIO_CERT_NASC, "
                + "PFIC_INFO_LIVRO_CERT_NASC, "
                + "PFIC_INFO_FOLHA_CERT_NASC, "
                + "PFIC_DATA_EXPED_CERT_NASC, "
                + "PFIC_MUNI_CODG_CERT_NASC, "
                + "PFIC_DESC_ENDR_EXTE_CERT_NASC, "
                + "PFIC_PAIS_CODG_CASAM, "
                + "PFIC_MUNI_CODG_CASAM, "
                + "PFIC_DESC_ENDR_EXTE_CERT_CASAM, "
                + "PFIC_INFO_CARTORIO_CERT_CASAM, "
                + "PFIC_NUMR_MATRICULA_CERT_CASAM, "
                + "PFIC_NUMR_CERT_CASAM, "
                + "PFIC_INFO_LIVRO_CERT_CASAM, "
                + "PFIC_INFO_FOLHA_CERT_CASAM, "
                + "PFIC_DATA_CASAM, "
                + "PFIC_NOME_CONJUGE) VALUES ("
                + this.iden + ", "
                + tratarNulo(this.dataObito) + ", "
                + tratarNulo(this.numrCNH) + ", "
                + tratarNulo(this.tipoCategCNH) + ", "
                + tratarNulo(this.dataExpedCNH) + ", "
                + tratarNulo(this.dataValidCNH) + ", "
                + tratarNulo(this.ufCnh) + ", "
                + tratarNulo(this.numrTitEleitor) + ", "
                + tratarNulo(this.zonaEleitor) + ", "
                + tratarNulo(this.secaoEleitor) + ", "
                + tratarNulo(this.dataExpedTitEleitor) + ", "
                + tratarNulo(this.codgMuniTitEleitor) + ","
                + tratarNulo(this.statServMilit == '\u0000' ? null : String.valueOf(this.statServMilit)) + ", "
                + tratarNulo(this.numrCertMilitar) + ", "
                + tratarNulo(this.siglForcaMilitar) + ", "
                + tratarNulo(this.codgRegiaoMilitar) + ", "
                + tratarNulo(this.numrPassaporte) + ", "
                + tratarNulo(this.dataVencPassaporte) + ", "
                + tratarNulo(this.codgPaisPassaporte) + ", "
                + tratarNulo(this.matriculaCertObito) + ", "
                + tratarNulo(this.numrCertObito) + ", "
                + tratarNulo(this.cartorioCertObito) + ", "
                + tratarNulo(this.livroCertObito) + ", "
                + tratarNulo(this.folhaCertObito) + ", "
                + tratarNulo(this.dataExpedCertObito) + ", "
                + tratarNulo(this.codgMunicObito) + ", "
                + tratarNulo(this.descCausaMortis) + ", "
                + tratarNulo(this.matriculaCertNasc) + ", "
                + tratarNulo(this.numrCertNasc) + ", "
                + tratarNulo(this.cartorioCertNasc) + ", "
                + tratarNulo(this.livroCertNasc) + ", "
                + tratarNulo(this.folhaCertNasc) + ", "
                + tratarNulo(this.dataExpedCertNasc) + ", "
                + tratarNulo(this.codgMuniCertNasc) + ", "
                + tratarNulo(this.cidadeEstadoEquivNasc) + ", "
                + tratarNulo(this.codgPaisCasamento) + ", "
                + tratarNulo(this.codgMuniCasamento) + ", "
                + tratarNulo(this.cidadeEstadoEquivCasamento) + ", "
                + tratarNulo(this.cartorioCertCasamento) + ", "
                + tratarNulo(this.matriculaCertCasamento) + ", "
                + tratarNulo(this.numrCertCasamento) + ", "
                + tratarNulo(this.livroCertCasamento) + ", "
                + tratarNulo(this.folhaCertCasamento) + ", "
                + tratarNulo(this.dataExpedCertCasamento) + ", "
                + tratarNulo(this.nomeConjuge) + ")";
        getObjTransacao().autenticarUsuarioPortal();
        if (getObjTransacao().executarSQL(sql) == 0) {
            erroMensagem = getObjTransacao().getErroMensagem();
            erroCodigo = getObjTransacao().getErroCodigo();
            return false;
        }

        return true;
    }

    /**
     * Alterar os dados complementares da pessoa Retorna um valor booleano
     * indicando se a alterao foi efetuada com sucesso. <p> Para executar este
     * mtodo  obrigatrio setar os seguintes mtodos: <p> setId() -- seta o
     * iden da pessoa <p> Podendo tambm ser setados outros mtodos mas no
     * obrigatrios: <p> setDataObito() -- seta a data de bito da pessoa <p>
     * setNumrCNH() -- seta o nmero da CNH da pessoa <p> setTipoCategCNH() --
     * seta o tipo da categoria da CNH da pessoa <p> setDataExpedCNH() -- seta a
     * data de expedio da CNH da pessoa <p> setDataValidCNH() -- seta a data
     * de validade da CNH da pessoa <p> setUFCNH() -- seta a UF da CNH da pessoa
     * <p> setNumrTitEleitor() -- seta o nmero do ttulo de eleitor da pessoa
     * <p> setCodgMuniTitEleitor() -- seta o codigo do municpio do titulo de
     * eleitor da pessoa <p> setZonaEleitor() -- seta a zona eleitoral da pessoa
     * <p> setSecaoEleitor() -- seta a seo eleitoral da pessoa <p>
     * setDataExpedTitEleitor() -- seta a data de expedio do ttulo de eleitor
     * da pessoa <p> setCodgMuniTitEleitor() -- seta o codigo do municpio do
     * titulo de eleitor da pessoa <p> setStatServMilit() -- seta o status do
     * servio militar da pessoa <p> setNumrCertMilitar() -- seta o nmero do
     * certificado militar da pessoa <p> setSiglForcaMilitar() -- seta a sigla
     * da fora militar da pessoa <p> setCodgRegiaoMilitar() -- seta o cdigo da
     * regio militar da pessoa <p> setNumrPassaporte() -- seta o nmero do
     * passaporte da pessoa <p> setDataVencPassaporte() -- seta a data de
     * vencimento do passaporte da pessoa <p> setPaisPassaporte() -- seta o
     * cdigo do pais do passaporte <p> setNumrCertObito() -- seta o nmero da
     * certido de bito da pessoa <p> setCartorioCertObito() -- seta o cartorio
     * da certido de bito da pessoa <p> setLivroCertObito() -- seta o livro da
     * certido de bito da pessoa <p> setFolhaCertObito() -- seta a folha da
     * certido de bito da pessoa <p> setDataExpedCertObito() -- seta a data da
     * expedio da certido de bito da pessoa <p> setCodgMunicObito() -- seta
     * o cdigo do municpio de bito da pessoa <p> setDescCausaMortis() -- seta
     * a descrio da causa mortis
     *
     * @return <i>true</i> se a alterao realizada com sucesso, e <i>false</i>
     * se houve algum erro. Quando o retorno for <i>false</i> os seguintes
     * mtodos podero ser usados:<p> <li>getErroMensagem() - mensagem que
     * especifica o problema encontrado.</li> <li>getErroCodigo() - cdigo do
     * problema encontrado.</li></p> Quando o retorno for <i>true</i> os
     * seguintes mtodos podero ser usados: <p> getDataObito() -- retorna a
     * data de bito da pessoa - tipo: java.util.Date <p> getNumrCNH() --
     * retorna o nmero da CNH da pessoa - tipo: long <p> getTipoCategCNH() --
     * retorna o tipo da categoria da CNH da pessoa - tipo: String <p>
     * getDataExpedCNH() -- retorna a data de expedio da CNH da pessoa - tipo:
     * java.util.Date <p> getDataValidCNH() -- retorna a data de validade da CNH
     * da pessoa - tipo: java.util.Date <p> getUFCNH() -- retorna a UF da CNH da
     * pessoa - tipo: String <p> getNumrTitEleitor() -- retorna o nmero do
     * ttulo de eleitor da pessoa - tipo: String <p> getZonaEleitor() --
     * retorna a zona eleitoral da pessoa - tipo: String <p> getSecaoEleitor()
     * -- retorna a seo eleitoral da pessoa - tipo: String <p>
     * getDataExpedTitEleitor() -- retorna a data de expedio do ttulo de
     * eleitor da pessoa - tipo: java.util.Date <p> getCodgMuniTitEleitor() --
     * retorna o codigo do municpio do titulo de eleitor da pessoa - tipo: int
     * <p> getStatServMilit() -- retorna o status do servio militar da pessoa -
     * tipo: char <p> getNumrCertMilitar() -- retorna o nmero do certificado
     * militar da pessoa - tipo: String <p> getSiglForcaMilitar() -- retorna a
     * sigla da fora militar da pessoa - tipo: String <p>
     * getCodgRegiaoMilitar() -- retorna o cdigo da regio militar da pessoa -
     * tipo: String <p> getNumrPassaporte() -- retorna o nmero do passaporte da
     * pessoa - tipo: String <p> getDataVencPassaporte() -- retorna a data de
     * vencimento do passaporte da pessoa - tipo: java.util.Date <p>
     * getPaisPassaporte() -- retorna o cdigo do pais do passaporte - tipo: int
     * <p> getNumrCertObito() -- retorna o nmero da certido de bito da pessoa
     * - tipo: String <p> getCartorioCertObito() -- retorna o cartorio da
     * certido de bito da pessoa - tipo: String <p> getLivroCertObito() --
     * retorna o livro da certido de bito da pessoa - tipo: String <p>
     * getFolhaCertObito() -- retorna a folha da certido de bito da pessoa -
     * tipo: String <p> getDataExpedCertObito() -- retorna a data da expedio
     * da certido de bito da pessoa - tipo: java.util.Date <p>
     * getCodgMunicObito() -- retorna o cdigo do municpio de bito da pessoa -
     * tipo: int <p> getDescCausaMortis() -- retorna a descrio da causa mortis
     * - tipo: String <p> getNumrCertNasc() -- retorna o nmero da certido de
     * nascimento - tipo: String <p> getCartorioCertNasc() -- retorna o nmero
     * do cartrio da certido de nascimento - tipo: String <p>
     * getLivroCertNasc() -- retorna o livro da certido de nascimento - tipo:
     * String <p> getFolhaCertNasc() -- retorna a folha da certido de
     * nascimento - tipo: String <p> getDataExpedCertNasc() -- retorna a data de
     * expedio da certido de nascimento - tipo: java.util.Date <p>
     * getCodgMuniCertNasc() -- retorna o cdigo do municpio da certido de
     * nascimento - tipo: int
     */
    private boolean alterarComplemento() {

        String strConector = "UPDATE PESSOAS_FISICAS_COMPLEMENTO SET ";
        sql = "";
        if (this.altDataObito) {
            sql = sql + strConector + "PFIC_DATA_OBITO = " + tratarNulo(this.dataObito);
            strConector = ",";
        }
        if (this.altNumrCNH) {
            sql = sql + strConector + "PFIC_NUMR_CART_MOTORISTA = " + tratarNulo(this.numrCNH);
            strConector = ",";
        }
        if (this.altTipoCategCNH) {
            sql = sql + strConector + "PFIC_TIPO_CATEG_CART_MOTORISTA = " + tratarNulo(this.tipoCategCNH);
            strConector = ",";
        }
        if (this.altDataExpedCNH) {
            sql = sql + strConector + "PFIC_DATA_EXPED_CART_MOTORISTA = " + tratarNulo(this.dataExpedCNH);
            strConector = ",";
        }
        if (this.altDataValidCNH) {
            sql = sql + strConector + "PFIC_DATA_VALID_CART_MOTORISTA = " + tratarNulo(this.dataValidCNH);
            strConector = ",";
        }
        if (this.altUFCNH) {
            sql = sql + strConector + "PFIC_UFED_SIGL_CART_MOTORISTA = " + tratarNulo(this.ufCnh);
            strConector = ",";
        }
        if (this.altNumrTitEleitor) {
            sql = sql + strConector + "PFIC_NUMR_TIT_ELEITOR = " + tratarNulo(this.numrTitEleitor);
            strConector = ",";
        }
        if (this.altZonaEleitor) {
            sql = sql + strConector + "PFIC_INFO_ZONA_TIT_ELEITOR = " + tratarNulo(this.zonaEleitor);
            strConector = ",";
        }
        if (this.altSecaoEleitor) {
            sql = sql + strConector + "PFIC_INFO_SECAO_TIT_ELEITOR = " + tratarNulo(this.secaoEleitor);
            strConector = ",";
        }
        if (this.altDataExpedTitEleitor) {
            sql = sql + strConector + "PFIC_DATA_EXPED_TIT_ELEITOR = " + tratarNulo(this.dataExpedTitEleitor);
            strConector = ",";
        }
        if (this.altCodgMuniTitEleitor) {
            sql = sql + strConector + "PFIC_MUNI_CODG_TIT_ELEITOR = " + tratarNulo(this.codgMuniTitEleitor);
            strConector = ",";
        }
        if (this.altStatServMilit) {
            sql = sql + strConector + "PFIC_STAT_SERV_MILITAR = " + tratarNulo(this.statServMilit == '\u0000' ? null : String.valueOf(this.statServMilit));
            strConector = ",";
        }
        if (this.altNumrCertMilitar) {
            sql = sql + strConector + "PFIC_NUMR_CERT_SERV_MILITAR = " + tratarNulo(this.numrCertMilitar);
            strConector = ",";
        }
        if (this.altSiglForcaMilitar) {
            sql = sql + strConector + "PFIC_SIGL_FORCA_SERV_MILITAR = " + tratarNulo(this.siglForcaMilitar);
            strConector = ",";
        }
        if (this.altCodgRegiaoMilitar) {
            sql = sql + strConector + "PFIC_CODG_REGIAO_SERV_MILITAR = " + tratarNulo(this.codgRegiaoMilitar);
            strConector = ",";
        }
        if (this.altNumrPassaporte) {
            sql = sql + strConector + "PFIC_NUMR_PASSAPORTE = " + tratarNulo(this.numrPassaporte);
            strConector = ",";
        }
        if (this.altDataVencPassaporte) {
            sql = sql + strConector + "PFIC_DATA_VENC_PASSAPORTE = " + tratarNulo(this.dataVencPassaporte);
            strConector = ",";
        }
        if (this.altCodgPaisPassaporte) {
            sql = sql + strConector + "PFIC_PAIS_CODG_PASSAPORTE = " + tratarNulo(this.codgPaisPassaporte);
            strConector = ",";
        }
        if (this.altMatriculaCertObito) {
            sql = sql + strConector + "PFIC_NUMR_MATRICULA_CERT_OBITO = " + tratarNulo(this.matriculaCertObito);
            strConector = ",";
        }
        if (this.altNumrCertObito) {
            sql = sql + strConector + "PFIC_NUMR_CERT_OBITO = " + tratarNulo(this.numrCertObito);
            strConector = ",";
        }
        if (this.altCartorioCertObito) {
            sql = sql + strConector + "PFIC_INFO_CARTORIO_CERT_OBITO = " + tratarNulo(this.cartorioCertObito);
            strConector = ",";
        }
        if (this.altLivroCertObito) {
            sql = sql + strConector + "PFIC_INFO_LIVRO_CERT_OBITO = " + tratarNulo(this.livroCertObito);
            strConector = ",";
        }
        if (this.altFolhaCertObito) {
            sql = sql + strConector + "PFIC_INFO_FOLHA_CERT_OBITO = " + tratarNulo(this.folhaCertObito);
            strConector = ",";
        }
        if (this.altDataExpedCertObito) {
            sql = sql + strConector + "PFIC_DATA_EXPED_CERT_OBITO = " + tratarNulo(this.dataExpedCertObito);
            strConector = ",";
        }
        if (this.altCodgMunicObito) {
            sql = sql + strConector + "PFIC_MUNI_CODG_OBITO = " + tratarNulo(this.codgMunicObito);
            strConector = ",";
        }
        if (this.altDescCausaMortis) {
            sql = sql + strConector + "PFIC_DESC_CAUSA_MORTIS = " + tratarNulo(this.descCausaMortis);
            strConector = ",";
        }
        if (this.altMatriculaCertNasc) {
            sql = sql + strConector + "PFIC_NUMR_MATRICULA_CERT_NASC = " + tratarNulo(this.matriculaCertNasc);
            strConector = ",";
        }
        if (this.altNumrCertNasc) {
            sql = sql + strConector + "PFIC_NUMR_CERT_NASC = " + tratarNulo(this.numrCertNasc);
            strConector = ",";
        }
        if (this.altCartorioCertNasc) {
            sql = sql + strConector + "PFIC_INFO_CARTORIO_CERT_NASC = " + tratarNulo(this.cartorioCertNasc);
            strConector = ",";
        }
        if (this.altCartorioCertCasamento) {
            sql = sql + strConector + "PFIC_INFO_CARTORIO_CERT_CASAM = " + tratarNulo(this.cartorioCertCasamento);
            strConector = ",";
        }
        if (this.altLivroCertNasc) {
            sql = sql + strConector + "PFIC_INFO_LIVRO_CERT_NASC = " + tratarNulo(this.livroCertNasc);
            strConector = ",";
        }
        if (this.altFolhaCertNasc) {
            sql = sql + strConector + "PFIC_INFO_FOLHA_CERT_NASC = " + tratarNulo(this.folhaCertNasc);
            strConector = ",";
        }
        if (this.altDataExpedCertNasc) {
            sql = sql + strConector + "PFIC_DATA_EXPED_CERT_NASC = " + tratarNulo(this.dataExpedCertNasc);
            strConector = ",";
        }
        if (this.altCodgMuniCertNasc) {
            sql = sql + strConector + "PFIC_MUNI_CODG_CERT_NASC = " + tratarNulo(this.codgMuniCertNasc);
            strConector = ",";
        }
        if (this.altCidadeEstadoEquivNasc) {
            sql = sql + strConector + "PFIC_DESC_ENDR_EXTE_CERT_NASC = " + tratarNulo(this.cidadeEstadoEquivNasc);
            strConector = ",";
        }
        if (this.altPaisCasamento) {
            sql = sql + strConector + "PFIC_PAIS_CODG_CASAM = " + tratarNulo(this.codgPaisCasamento);
            strConector = ",";
        }
        if (this.altCodgMuniCasamento) {
            sql = sql + strConector + "PFIC_MUNI_CODG_CASAM = " + tratarNulo(this.codgMuniCasamento);
            strConector = ",";
        }
        if (this.altCidadeEstadoEquivCasamento) {
            sql = sql + strConector + "PFIC_DESC_ENDR_EXTE_CERT_CASAM = " + tratarNulo(this.cidadeEstadoEquivCasamento);
            strConector = ",";
        }
        if (this.altMatriculaCertCasamento) {
            sql = sql + strConector + "PFIC_NUMR_MATRICULA_CERT_CASAM = " + tratarNulo(this.matriculaCertCasamento);
            strConector = ",";
        }
        if (this.altNumrCertCasamento) {
            sql = sql + strConector + "PFIC_NUMR_CERT_CASAM = " + tratarNulo(this.numrCertCasamento);
            strConector = ",";
        }
        if (this.altLivroCertCasamento) {
            sql = sql + strConector + "PFIC_INFO_LIVRO_CERT_CASAM = " + tratarNulo(this.livroCertCasamento);
            strConector = ",";
        }
        if (this.altFolhaCertCasamento) {
            sql = sql + strConector + "PFIC_INFO_FOLHA_CERT_CASAM = " + tratarNulo(this.folhaCertCasamento);
            strConector = ",";
        }
        if (this.altDataExpedCertCasamento) {
            sql = sql + strConector + "PFIC_DATA_CASAM = " + tratarNulo(this.dataExpedCertCasamento);
            strConector = ",";
        }
        if (this.altNomeConjuge) {
            sql = sql + strConector + "PFIC_NOME_CONJUGE = " + tratarNulo(this.nomeConjuge);
            strConector = ",";
        }
        if (!sql.equals("")) {

            sql += " WHERE PFIC_PFIS_IDEN  = " + this.iden;

            getObjTransacao().autenticarUsuarioPortal();
            if (getObjTransacao().executarSQL(sql) == 0) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return false;
            }
        }
        return true;
    }

    /**
     * Excluir Pessoas_Fisicas_Complemento Retorna um valor booleano indicando
     * se a alterao foi efetuada com sucesso. <p> Para executar este mtodo 
     * obrigatrio setar os seguintes mtodos: <p> setId() -- seta o iden da
     * pessoa
     *
     * @return <i>true</i> se a excluso realizada com sucesso, e <i>false</i>
     * se houve algum erro. Quando o retorno for <i>false</i> os seguintes
     * mtodos podero ser usados:<p> <li>getErroMensagem() - mensagem que
     * especifica o problema encontrado.</li> <li>getErroCodigo() - código do
     * problema encontrado.</li></p>
     */
    private boolean excluirComplemento() {

        try {

            sql = "DELETE PESSOAS_FISICAS_COMPLEMENTO"
                    + " WHERE PFIC_PFIS_IDEN = " + this.iden;

            getObjTransacao().autenticarUsuarioPortal();
            if (getObjTransacao().executarSQL(sql) != 1) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return false;
            }

            return true;

        } catch (Exception e) {
            erroMensagem = "Erro ao excluir Pessoa Fisica Complemento. " + e.getMessage();
            return false;
        }
    }

    /**
     * Consulta o cdigo das pessoas apartir dos fonemas derivados do seu nome.
     *
     * @param nome Nome da Pessoa
     * @return true ou false
     */
    public final boolean consultarNome(String nome) {
        String restoSql = "pfis_iden in (" + this.montarSqlFonetico(Pessoa.tratarNome(nome), FON_NOME) + ") AND ROWNUM < 1000";
        if (!this.consultarPessoa(restoSql)) {
            return false;
        }
        return true;
    }

    public final boolean consultarNomeDataNascimento(String nome, String dataNascimento) {
        String restoSql = "pfis_iden in (" + this.montarSqlFonetico(Pessoa.tratarNome(nome), FON_NOME) + ") AND PFIS_DATA_NASC = TO_DATE('" + dataNascimento + "', 'DD/MM/YYYY')";
        
        return this.consultarPessoa(restoSql);
    }    
    
    /**
     * Consulta o cdigo das pessoas apartir dos fonemas derivados do nome da
     * me.
     *
     * @param nome Nome da Mae
     * @return true ou false
     */
    public final boolean consultarMae(String nome) {
        String restoSql = "pfis_iden in (" + this.montarSqlFonetico(Pessoa.tratarNome(nome), FON_MAE) + ")";
        if (!this.consultarPessoa(restoSql)) {
            return false;
        }
        return true;
    }

    /**
     * Consulta pessoa fsica pelo nome e/ou data de nascimento e/ou nome da me
     * a partir dos fonemas derivados do seu nome e/ou data de nascimento e/ou
     * nome da me.
     *
     * @param nome nome da pessoa fsica - tipo:<b>String</b>
     * @param dataNascimento data de nascimento da pessoa fsica -
     * tipo:<b>java.util.Date</b>
     * @param nomeMae nome da me da pessoa fsica - tipo: <b>String</b>
     * @return <i><b>ResultSet</b></i> se consulta realizada com sucesso e
     * <i><b>null</b></i> se ocorreu algum erro. <p>Caso retorne
     * <i><b>ResultSet</b></i> as seguintes colunas podero ser usados: <ul
     * type="square"><li> PFIS_IDEN - <b>long</b> - o id da pessoa fsica
     * </li></ul> <ul type="square"><li> PFIS_NUMR_CPF - <b>long</b> - o nmero
     * do CPF da pessoa fsica</li></ul> <ul type="square"><li> PFIS_NOME -
     * <b>String</b> - o nome da pessoa fsica</li></ul> <ul type="square"><li>
     * PFIS_NOME_MAE - <b>String</b> - o nome da me da pessoa fsica</li></ul>
     * <ul type="square"><li> PFIS_DATA_NASC - <b>java.util.Date</b> - a data de
     * nascimento da pessoa fsica</li></ul> <ul type="square"><li>
     * PFIS_SIGL_SEXO - <b>char</b> - o sexo da pessoa fsica</li></ul> <p> Caso
     * retorne <i><b>null</i></b> os sequintes mtodos estaro disponveis: <ul
     * type="square"><li> getErroCodigo() - retorna o cdigo do erro
     * ocorrido</li></ul> <ul type="square"><li> getErroMensagem() - retorna a
     * mensagem gerada pelo erro</li></ul>
     */
    public final ResultSet getPessoas(String nome, java.util.Date dataNascimento, String nomeMae) {

        sql = "SELECT /*+ RULE */ PFIS_IDEN, "
                + "PFIS_NOME, "
                + "PFIS_NOME_MAE, "
                + "PFIS_NOME_PAI, "
                + "PFIS_DATA_NASC, "
                + "PFIS_SIGL_SEXO, "
                + "PFIS_NUMR_CPF, "
                + "PFIS_NUMR_RG, "
                + "PFIS_DATA_EXPED_RG, "
                + "PFIS_SIGL_ORGAO_RG, "
                + "PFIS_UFED_SIGL_RG, "
                + "PFIS_TIPO_ESTADO_CIVIL "
                + "FROM PESSOAS_FISICAS "
                + "WHERE ";

        String restoSql = "";

        if (nome != null && !nome.equals("")) {
            restoSql += "PFIS_IDEN IN(" + super.montarSqlFonetico(nome, "N") + ")";
        }
        if (nomeMae != null && !nomeMae.equals("")) {
            if (!restoSql.equals("")) {
                restoSql += " AND ";
            }
            restoSql += "PFIS_IDEN IN(" + super.montarSqlFonetico(nomeMae, "M") + ")";
        }
        if (dataNascimento != null) {
            if (!restoSql.equals("")) {
                restoSql += " AND ";
            }
            restoSql += "PFIS_DATA_NASC = TO_DATE('" + sdf.format(dataNascimento) + "','DDMMYYYY')";
        }

        sql += restoSql;

        ResultSet objRS = getObjTransacao().consultarSQL(sql);
        if (objRS == null) {
            erroMensagem = getObjTransacao().getErroMensagem();
            erroCodigo = getObjTransacao().getErroCodigo();
            return null;
        }
        return objRS;
    }

    /**
     * Avança um registro quando uma consulta retornar mais que um registro
     *
     * @return true ou false para fim de registros
     */
    public final boolean avancar() {
        this.limparPropriedades();
        try {
            if (!rset.next()) {
                return false;
            }
            this.tipo = FISICA;
            this.iden = rset.getLong("PFIS_IDEN");
            this.nome = rset.getString("PFIS_NOME");
            this.numrCPF = rset.getLong("PFIS_NUMR_CPF");
            this.dataNascimento = rset.getDate("PFIS_DATA_NASC");
            this.numrRG = rset.getString("PFIS_NUMR_RG");
            this.nomeMae = rset.getString("PFIS_NOME_MAE");
            this.nomePai = rset.getString("PFIS_NOME_PAI");
            this.paisNacionalidade = rset.getInt("PFIS_PAIS_CODG_NACIONALIDADE");
            this.ufRg = rset.getString("PFIS_UFED_SIGL_RG");
            this.dataExpedicaoRG = rset.getDate("PFIS_DATA_EXPED_RG");
            this.orgaoRG = rset.getString("PFIS_SIGL_ORGAO_RG");
            this.numrMatrBase = rset.getLong("PFIS_NUMR_MATRICULA_BASE");
            this.grauInstrucao = rset.getInt("PFIS_GINS_CODG");
            this.tipoEstadoCivil = rset.getString("PFIS_TIPO_ESTADO_CIVIL") == null ? '\u0000' : rset.getString("PFIS_TIPO_ESTADO_CIVIL").charAt(0);
            this.codgPaisNatu = rset.getInt("PFIS_PAIS_CODG_NASCIMENTO");
            this.codgMuniNatu = rset.getInt("PFIS_MUNI_CODG_NATURALIDADE");
            this.sexo = rset.getString("PFIS_SIGL_SEXO");
            this.anoChegadaBrasil = rset.getInt("PFIS_NUMR_ANO_CHEGADA_BRASIL");
            this.tipoSanguineo = rset.getString("PFIS_TIPO_SANGUINEO");
            this.tipoFatorRh = rset.getString("PFIS_TIPO_FATOR_RH") == null ? '\u0000' : rset.getString("PFIS_TIPO_FATOR_RH").charAt(0);
            this.indicadorDoador = rset.getString("PFIS_INDI_DOADOR") == null ? '\u0000' : rset.getString("PFIS_INDI_DOADOR").charAt(0);
            this.dataNaturalizacao = rset.getDate("PFIS_DATA_NATURALIZACAO");
            this.dataExpedicaoCIN = rset.getDate("PFIS_DATA_EMISSAO_CIN");
            this.orgaoCIN = rset.getString("PFIS_SIGL_ORGAO_CIN");
            this.codMunicipio = rset.getString("PFIS_MUNI_CODG_EMISSAO_CIN");
            this.txtUF = rset.getString("MUNI_UFED_SIGL");
            this.txtNomeUF = rset.getString("UFED_NOME");
            this.nomeMunicipio = rset.getString("MUNI_NOME");
            return true;

        } catch (SQLException e) {
            erroMensagem = e.getMessage();
            erroCodigo = e.getErrorCode();
            return false;
        }
    }

    private boolean consultarPessoa(String restoSql) {
        sql = "SELECT /*+ RULE */ PFIS_IDEN,PFIS_NOME,PFIS_NOME_MAE,PFIS_NOME_PAI,PFIS_DATA_NASC"
                + ",PFIS_SIGL_SEXO,PFIS_NUMR_CPF,PFIS_NUMR_RG,PFIS_DATA_EXPED_RG"
                + ",PFIS_SIGL_ORGAO_RG,PFIS_UFED_SIGL_RG,PFIS_NUMR_MATRICULA_BASE"
                + ",PFIS_TIPO_SANGUINEO,PFIS_TIPO_FATOR_RH,PFIS_INDI_DOADOR"
                + ",PFIS_TIPO_ESTADO_CIVIL,PFIS_TIPO_GRAU_INSTRUCAO"
                + ",PFIS_MUNI_CODG_NATURALIDADE,PFIS_PAIS_CODG_NASCIMENTO,PFIS_GINS_CODG"
                + ",PFIS_PAIS_CODG_NACIONALIDADE,PFIS_NUMR_ANO_CHEGADA_BRASIL"
                + ",PFIS_DATA_NATURALIZACAO,PFIS_SIGL_ORGAO_CIN,PFIS_DATA_EMISSAO_CIN,PFIS_MUNI_CODG_EMISSAO_CIN"
                + ",MUNI_CODG,MUNI_NOME,MUNI_UFED_SIGL,UFED_SIGL,UFED_NOME"
                + " FROM PESSOAS_FISICAS, MUNICIPIOS, UNIDADES_FEDERATIVAS"
                + " WHERE PFIS_MUNI_CODG_EMISSAO_CIN = MUNI_CODG(+) AND MUNI_UFED_SIGL = UFED_SIGL(+) AND " + restoSql
                + " ORDER BY PFIS_NOME,PFIS_DATA_NASC";
        
        rset = getObjTransacao().consultarSQL(sql);
        
        if (rset == null) {
            erroMensagem = getObjTransacao().getErroMensagem();
            erroCodigo = getObjTransacao().getErroCodigo();
            return false;
        }
        if (!this.avancar()) {
            //       erroMensagem =   "Consulta no encontrada";
            //       erroCodigo = ERRO_CONSULTA_INVALIDA;
            return false;
        }
        this.consultarComplemento(true);
        return true;
    }

    /**
     * Consulta os dados complementares da pessoa
     *
     * @param atualizarLocal indica se as variveis da classe vo ser setadas ou
     * no. <p> Para executar este mtodo  obrigatrio setar os seguintes
     * mtodos: <p> setId() -- seta o iden da pessoa Retorna um valor booleano
     * indicando se a consulta foi efetuada com sucesso.
     * @return <i>true</i> se a consulta realizada com sucesso, e <i>false</i>
     * se houve algum erro. Quando o retorno for <i>false</i> os seguintes
     * mtodos podero ser usados:<p> <li>getErroMensagem() - mensagem que
     * especifica o problema encontrado.</li> <li>getErroCodigo() - cdigo do
     * problema encontrado.</li></p> Quando o retorno for <i>true</i> os
     * seguintes mtodos podero ser usados: <p> getDataObito() -- retorna a
     * data de bito da pessoa - tipo: java.util.Date <p> getNumrCNH() --
     * retorna o nmero da CNH da pessoa - tipo: long <p> getTipoCategCNH() --
     * retorna o tipo da categoria da CNH da pessoa - tipo: String <p>
     * getDataExpedCNH() -- retorna a data de expedio da CNH da pessoa - tipo:
     * java.util.Date <p> getDataValidCNH() -- retorna a data de validade da CNH
     * da pessoa - tipo: java.util.Date <p> getUFCNH() -- retorna a UF da CNH da
     * pessoa - tipo: String <p> getNumrTitEleitor() -- retorna o nmero do
     * ttulo de eleitor da pessoa - tipo: String <p> getZonaEleitor() --
     * retorna a zona eleitoral da pessoa - tipo: String <p> getSecaoEleitor()
     * -- retorna a seo eleitoral da pessoa - tipo: String <p>
     * getDataExpedTitEleitor() -- retorna a data de expedio do ttulo de
     * eleitor da pessoa - tipo: java.util.Date <p> getCodgMuniTitEleitor() --
     * retorna o codigo do municpio do titulo de eleitor da pessoa - tipo: int
     * <p> getStatServMilit() -- retorna o status do servio militar da pessoa -
     * tipo: char <p> getNumrCertMilitar() -- retorna o nmero do certificado
     * militar da pessoa - tipo: String <p> getSiglForcaMilitar() -- retorna a
     * sigla da fora militar da pessoa - tipo: String <p>
     * getCodgRegiaoMilitar() -- retorna o cdigo da regio militar da pessoa -
     * tipo: String <p> getNumrPassaporte() -- retorna o nmero do passaporte da
     * pessoa - tipo: String <p> getDataVencPassaporte() -- retorna a data de
     * vencimento do passaporte da pessoa - tipo: java.util.Date <p>
     * getPaisPassaporte() -- retorna o cdigo do pais do passaporte - tipo: int
     * <p> getNumrCertObito() -- retorna o nmero da certido de bito da pessoa
     * - tipo: String <p> getCartorioCertObito() -- retorna o cartorio da
     * certido de bito da pessoa - tipo: String <p> getLivroCertObito() --
     * retorna o livro da certido de bito da pessoa - tipo: String <p>
     * getFolhaCertObito() -- retorna a folha da certido de bito da pessoa -
     * tipo: String <p> getDataExpedCertObito() -- retorna a data da expedio
     * da certido de bito da pessoa - tipo: java.util.Date <p>
     * getCodgMunicObito() -- retorna o cdigo do municpio de bito da pessoa -
     * tipo: int <p> getDescCausaMortis() -- retorna a descrio da causa mortis
     * - tipo: String <p> getNumrCertNasc() -- retorna o nmero da certido de
     * nascimento - tipo: String <p> getCartorioCertNasc() -- retorna o nmero
     * do cartrio da certido de nascimento - tipo: String <p>
     * getLivroCertNasc() -- retorna o livro da certido de nascimento - tipo:
     * String <p> getFolhaCertNasc() -- retorna a folha da certido de
     * nascimento - tipo: String <p> getDataExpedCertNasc() -- retorna a data de
     * expedio da certido de nascimento - tipo: java.util.Date <p>
     * getCodgMuniCertNasc() -- retorna o cdigo do municpio da certido de
     * nascimento - tipo: int
     */
    private boolean consultarComplemento(boolean atualizarLocal) {

        if (leuComplemento) {
            return true;
        }
        sql = "SELECT PFIC_DATA_OBITO, "
                + "PFIC_NUMR_CART_MOTORISTA, "
                + "PFIC_TIPO_CATEG_CART_MOTORISTA, "
                + "PFIC_DATA_EXPED_CART_MOTORISTA, "
                + "PFIC_DATA_VALID_CART_MOTORISTA, "
                + "PFIC_UFED_SIGL_CART_MOTORISTA, "
                + "PFIC_NUMR_TIT_ELEITOR, "
                + "PFIC_INFO_ZONA_TIT_ELEITOR, "
                + "PFIC_INFO_SECAO_TIT_ELEITOR, "
                + "PFIC_DATA_EXPED_TIT_ELEITOR, "
                + "PFIC_MUNI_CODG_TIT_ELEITOR, "
                + "PFIC_STAT_SERV_MILITAR, "
                + "PFIC_NUMR_CERT_SERV_MILITAR, "
                + "PFIC_SIGL_FORCA_SERV_MILITAR, "
                + "PFIC_CODG_REGIAO_SERV_MILITAR, "
                + "PFIC_NUMR_PASSAPORTE, "
                + "PFIC_DATA_VENC_PASSAPORTE, "
                + "PFIC_PAIS_CODG_PASSAPORTE, "
                + "PFIC_NUMR_MATRICULA_CERT_OBITO, "
                + "PFIC_NUMR_CERT_OBITO, "
                + "PFIC_INFO_CARTORIO_CERT_OBITO, "
                + "PFIC_INFO_LIVRO_CERT_OBITO, "
                + "PFIC_INFO_FOLHA_CERT_OBITO, "
                + "PFIC_DATA_EXPED_CERT_OBITO, "
                + "PFIC_MUNI_CODG_OBITO, "
                + "PFIC_DESC_CAUSA_MORTIS, "
                + "PFIC_NUMR_MATRICULA_CERT_NASC, "
                + "PFIC_NUMR_CERT_NASC, "
                + "PFIC_INFO_CARTORIO_CERT_NASC, "
                + "PFIC_INFO_LIVRO_CERT_NASC, "
                + "PFIC_INFO_FOLHA_CERT_NASC, "
                + "PFIC_DATA_EXPED_CERT_NASC, "
                + "PFIC_MUNI_CODG_CERT_NASC, "
                + "PFIC_DESC_ENDR_EXTE_CERT_NASC, "
                + "PFIC_PAIS_CODG_CASAM, "
                + "PFIC_MUNI_CODG_CASAM, "
                + "PFIC_DESC_ENDR_EXTE_CERT_CASAM, "
                + "PFIC_NUMR_MATRICULA_CERT_CASAM, "
                + "PFIC_NUMR_CERT_CASAM, "
                + "PFIC_INFO_LIVRO_CERT_CASAM, "
                + "PFIC_INFO_FOLHA_CERT_CASAM, "
                + "PFIC_DATA_CASAM, "
                + "PFIC_NOME_CONJUGE, "
                + "PFIC_INFO_CARTORIO_CERT_CASAM "
                + "FROM PESSOAS_FISICAS_COMPLEMENTO "
                + "WHERE PFIC_PFIS_IDEN = " + this.iden;
        ResultSet objRS = null;
        try {
            objRS = getObjTransacao().consultarSQL(sql);
            if (objRS == null || !objRS.next()) {
                erroMensagem = objRS == null ? getObjTransacao().getErroMensagem()
                        : "Consulta no encontrada";
                erroCodigo = objRS == null ? getObjTransacao().getErroCodigo() : ERRO_CONSULTA_INVALIDA;
                return false;
            } else {
                if (atualizarLocal) {
                    this.dataObito = objRS.getDate("PFIC_DATA_OBITO");
                    this.numrCNH = objRS.getLong("PFIC_NUMR_CART_MOTORISTA");
                    this.tipoCategCNH = objRS.getString("PFIC_TIPO_CATEG_CART_MOTORISTA");
                    this.dataExpedCNH = objRS.getDate("PFIC_DATA_EXPED_CART_MOTORISTA");
                    this.dataValidCNH = objRS.getDate("PFIC_DATA_VALID_CART_MOTORISTA");
                    this.ufCnh = objRS.getString("PFIC_UFED_SIGL_CART_MOTORISTA");
                    this.numrTitEleitor = objRS.getString("PFIC_NUMR_TIT_ELEITOR");
                    this.zonaEleitor = objRS.getString("PFIC_INFO_ZONA_TIT_ELEITOR");
                    this.secaoEleitor = objRS.getString("PFIC_INFO_SECAO_TIT_ELEITOR");
                    this.dataExpedTitEleitor = objRS.getDate("PFIC_DATA_EXPED_TIT_ELEITOR");
                    this.codgMuniTitEleitor = objRS.getInt("PFIC_MUNI_CODG_TIT_ELEITOR");
                    this.statServMilit = objRS.getString("PFIC_STAT_SERV_MILITAR") == null ? '\u0000' : objRS.getString("PFIC_STAT_SERV_MILITAR").charAt(0);
                    this.numrCertMilitar = objRS.getString("PFIC_NUMR_CERT_SERV_MILITAR");
                    this.siglForcaMilitar = objRS.getString("PFIC_SIGL_FORCA_SERV_MILITAR");
                    this.codgRegiaoMilitar = objRS.getString("PFIC_CODG_REGIAO_SERV_MILITAR");
                    this.numrPassaporte = objRS.getString("PFIC_NUMR_PASSAPORTE");
                    this.dataVencPassaporte = objRS.getDate("PFIC_DATA_VENC_PASSAPORTE");
                    this.codgPaisPassaporte = objRS.getInt("PFIC_PAIS_CODG_PASSAPORTE");
                    this.matriculaCertObito = objRS.getString("PFIC_NUMR_MATRICULA_CERT_OBITO");
                    this.numrCertObito = objRS.getString("PFIC_NUMR_CERT_OBITO");
                    this.cartorioCertObito = objRS.getString("PFIC_INFO_CARTORIO_CERT_OBITO");
                    this.livroCertObito = objRS.getString("PFIC_INFO_LIVRO_CERT_OBITO");
                    this.folhaCertObito = objRS.getString("PFIC_INFO_FOLHA_CERT_OBITO");
                    this.dataExpedCertObito = objRS.getDate("PFIC_DATA_EXPED_CERT_OBITO");
                    this.codgMunicObito = objRS.getInt("PFIC_MUNI_CODG_OBITO");
                    this.descCausaMortis = objRS.getString("PFIC_DESC_CAUSA_MORTIS");
                    this.matriculaCertNasc = objRS.getString("PFIC_NUMR_MATRICULA_CERT_NASC");
                    this.numrCertNasc = objRS.getString("PFIC_NUMR_CERT_NASC");
                    this.cartorioCertNasc = objRS.getString("PFIC_INFO_CARTORIO_CERT_NASC");
                    this.livroCertNasc = objRS.getString("PFIC_INFO_LIVRO_CERT_NASC");
                    this.folhaCertNasc = objRS.getString("PFIC_INFO_FOLHA_CERT_NASC");
                    this.dataExpedCertNasc = objRS.getDate("PFIC_DATA_EXPED_CERT_NASC");
                    this.codgMuniCertNasc = objRS.getInt("PFIC_MUNI_CODG_CERT_NASC");
                    this.cidadeEstadoEquivNasc = objRS.getString("PFIC_DESC_ENDR_EXTE_CERT_NASC");
                    this.codgPaisCasamento = objRS.getInt("PFIC_PAIS_CODG_CASAM");
                    this.codgMuniCasamento = objRS.getInt("PFIC_MUNI_CODG_CASAM");
                    this.cidadeEstadoEquivCasamento = objRS.getString("PFIC_DESC_ENDR_EXTE_CERT_CASAM");
                    this.matriculaCertCasamento = objRS.getString("PFIC_NUMR_MATRICULA_CERT_CASAM");
                    this.numrCertCasamento = objRS.getString("PFIC_NUMR_CERT_CASAM");
                    this.livroCertCasamento = objRS.getString("PFIC_INFO_LIVRO_CERT_CASAM");
                    this.folhaCertCasamento = objRS.getString("PFIC_INFO_FOLHA_CERT_CASAM");
                    this.dataExpedCertCasamento = objRS.getDate("PFIC_DATA_CASAM");
                    this.nomeConjuge = objRS.getString("PFIC_NOME_CONJUGE");
                    this.cartorioCertCasamento = objRS.getString("PFIC_INFO_CARTORIO_CERT_CASAM");
                }
                leuComplemento = true;
                return true;
            }
        } catch (SQLException e) {
            erroMensagem = e.getMessage();
            erroCodigo = e.getErrorCode();
            return false;
        } finally {
            try {
                if (objRS != null) {
                    objRS.close();
                }
            } catch (SQLException ex) {
                Logger.getRootLogger().error("Erro ao fechar ResultSet: " + ex.getMessage());
            }
        }
    }

    /**
     * Consulta data do Óbito
     *
     * @return data do Óbito
     */
    public final java.util.Date getDataObito() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        if (this.dataObito == null) {
            return null;
        } else {
            return (java.util.Date) this.dataObito.clone();
        }
    }

    /**
     * Consulta o número da CNH
     *
     * @return O número da CNH - tipo: <b>long</b>
     */
    public final long getNumrCNH() {

        if (!this.consultarComplemento(true)) {
            return 0;
        }

        return this.numrCNH;
    }

    /**
     * Consulta Categoria da CNH
     *
     * @return Categoria da CNH - tipo: <b>String</b>
     */
    public final String getTipoCategCNH() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.tipoCategCNH;
    }

    /**
     * Consulta a UF da CNH
     *
     * @return a UF da CNH - tipo: <b>String</b> 
     */
    public final String getUFCNH() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.ufCnh;
    }

    /**
     * Consulta Data da expedição da CNH
     *
     * @return Data da expedição da CNH - tipo: <b>java.util.Date</b>
     */
    public final java.util.Date getDataExpedCNH() {

        if (!this.consultarComplemento(true)) {
            return null;
        }
        if (this.dataExpedCNH == null) {
            return null;
        } else {
            return (java.util.Date) this.dataExpedCNH.clone();
        }
    }

    /**
     * Consulta Data da validação da CNH
     *
     * @return Data da validação da CNH - tipo: <b>java.util.Date</b>
     */
    public final java.util.Date getDataValidCNH() {

        if (!this.consultarComplemento(true)) {
            return null;
        }
        if (this.dataValidCNH == null) {
            return null;
        } else {
            return (java.util.Date) this.dataValidCNH.clone();
        }
    }

    /**
     * Consulta número do titulo do eleitor
     *
     * @return número do titulo do eleitor - tipo: <b>String</b>
     */
    public final String getNumrTitEleitor() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.numrTitEleitor;
    }

    /**
     * Consulta zona de votao do eleitor
     *
     * @return Zona de votao do eleitor - tipo: <b>String</b>
     */
    public final String getZonaEleitor() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.zonaEleitor;
    }

    /**
     * Consulta setor de votação do eleitor
     *
     * @return Setor de votação do eleitor - tipo: <b>String</b>
     */
    public final String getSecaoEleitor() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.secaoEleitor;
    }

    /**
     * Consulta data de expedição do ttulo do eleitor
     *
     * @return Data de expedição do ttulo do eleitor - tipo:
     * <b>java.util.Date</b>
     */
    public final java.util.Date getDataExpedTitEleitor() {

        if (!this.consultarComplemento(true)) {
            return null;
        }
        if (this.dataExpedTitEleitor == null) {
            return null;
        } else {
            return (java.util.Date) this.dataExpedTitEleitor.clone();
        }
    }

    /**
     * Consulta status do servio militar
     *
     * @return Status do servio militar - tipo: <b>char</b>
     */
    public final char getStatServMilit() {

        if (!this.consultarComplemento(true)) {
            return '\u0000';
        }

        return this.statServMilit;
    }

    /**
     * Consulta número do certificado de serviço militar
     *
     * @return número do certificado de serviço militar - tipo: <b>String</b>
     */
    public final String getNumrCertMilitar() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.numrCertMilitar;
    }

    /**
     * Consulta sigla da fora do serviço militar
     *
     * @return Sigla da fora do serviço militar - tipo: <b>String</b>
     */
    public final String getSiglForcaMilitar() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.siglForcaMilitar;
    }

    /**
     * Consulta código da região militar
     *
     * @return Código da região militar - tipo: <b>String</b>
     */
    public final String getCodgRegiaoMilitar() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.codgRegiaoMilitar;
    }

    /**
     * Consulta número da certidão de óbito
     *
     * @return número da certidão de óbito - tipo: <b>String</b>
     */
    public final String getNumrCertObito() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.numrCertObito;
    }

    /**
     * Consulta número do passaporte
     *
     * @return Número ddo passaporte - tipo: <b>String</b>
     */
    public final String getNumrPassaporte() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.numrPassaporte;
    }

    /**
     * Consulta o tipo sanguineo
     *
     * @return o tipo sanguineo - tipo: <b>String</b>
     */
    public final String getTipoSanguineo() {
        return this.tipoSanguineo;
    }

    /**
     * Consulta o fator RH
     *
     * @return o fator RH - tipo: <b>char</b>
     */
    public final char getFatorRH() {
        return this.tipoFatorRh;
    }

    /**
     * Consulta o indicador doador
     *
     * @return O indicador doador - tipo: <b>char</b>
     */
    public final char getIndicadorDoador() {
        return this.indicadorDoador;
    }

    /**
     * Consulta cartório da certidão de Óbito
     *
     * @return Cartório da certidão de Óbito - tipo: <b>String</b>
     */
    public final String getCartorioCertObito() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.cartorioCertObito;
    }

    /**
     * Consulta livro da certidão de Óbito
     *
     * @return Livro da certidão de Óbito - tipo: <b>String</b>
     */
    public final String getLivroCertObito() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.livroCertObito;
    }

    /**
     * Consulta folha da certidão de Óbito
     *
     * @return Folha da certidão de Óbito - tipo: <b>String</b>
     */
    public final String getFolhaCertObito() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.folhaCertObito;
    }

    /**
     * Consulta data de expedição da certidão de Óbito
     *
     * @return Data de expedição da certidão de Óbito - tipo:
     * <b>java.util.Date</b>
     */
    public final java.util.Date getDataExpedCertObito() {

        if (!this.consultarComplemento(true)) {
            return null;
        }
        if (this.dataExpedCertObito == null) {
            return null;
        } else {
            return (java.util.Date) this.dataExpedCertObito.clone();
        }
    }

    /**
     * Consulta código do municpio de Óbito
     *
     * @return código do municpio de Óbito - tipo: <b>int</b>
     */
    public final int getCodgMunicObito() {

        if (!this.consultarComplemento(true)) {
            return 0;
        }

        return this.codgMunicObito;
    }

    /**
     * Consulta descrição da causa
     *
     * @return Descrição da causa - tipo: <b>String</b>
     */
    public final String getDescCausaMortis() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.descCausaMortis;
    }

    /**
     * Consulta número da certidão de nascimento
     *
     * @return numrCertNasc - tipo: <b>String</b>    
     */
    public final String getNumrCertNasc() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.numrCertNasc;
    }

    /**
     * Consulta número do cartório da certidão de nascimento
     *
     * @return cartorioCertNasc - tipo: <b>String</b>
     */
    public final String getCartorioCertNasc() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.cartorioCertNasc;
    }

    /**
     * Consulta livro da certidão de nascimento
     *
     * @return livroCertNasc - tipo: <b>String</b>     
     */
    public final String getLivroCertNasc() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.livroCertNasc;
    }

    /**
     * Consulta folha da certidão de nascimento
     *
     * @return folhaCertNasc - tipo: <b>String</b>     
     */
    public final String getFolhaCertNasc() {

        if (!this.consultarComplemento(true)) {
            return null;
        }

        return this.folhaCertNasc;
    }

    /**
     * Consulta data de expedição da certido de nascimento
     *
     * @return dataExpedCertNasc - tipo: <b>Date</b>     
     */
    public final java.util.Date getDataExpedCertNasc() {

        if (!this.consultarComplemento(true)) {
            return null;
        }
        if (this.dataExpedCertNasc == null) {
            return null;
        } else {
            return (java.util.Date) this.dataExpedCertNasc.clone();
        }
    }

    /**
     * Consulta código do municpio da expedição de nascimento
     *
     * @return codgMuniCertNasc - tipo: <b>int</b>     
     */
    public final int getCodgMuniCertNasc() {

        if (!this.consultarComplemento(true)) {
            return 0;
        }

        return this.codgMuniCertNasc;
    }

    /**
     * Consulta o nome da pessoa
     *
     * @return nome da pessoa
     */
    public final String getNome() {
        return this.nome;
    }

    /**
     * Consulta o CPF
     *
     * @return CPF
     */
    public final long getCPF() {
        return this.numrCPF;
    }

    /**
     * Retorna o CPF Formatado da pessoa
     *
     * @return CPF Formatado da pessoa
     */
    public final String getCPFFormatado() {
        return formatarCPFCNPJ(String.valueOf(this.getCPF()));

    }

    /**
     * Consulta a Data de Nascimento
     *
     * @return Data de Nascimento
     */
    public final java.util.Date getDataNascimento() {
        if (this.dataNascimento != null) {
            return (java.util.Date) this.dataNascimento.clone();
        } else {
            return null;
        }
    }

    /**
     * Numero da identidade da pessoa física
     *
     * @return Numero da identidade da pessoa física
     */
    public final String getNumrRG() {
        return this.numrRG;
    }

    /**
     * Nome da Mãe da pessoa física
     *
     * @return Nome da Mãe da pessoa física
     */
    public final String getNomeMae() {
        return this.nomeMae;
    }

    /**
     * Nome do pai da pessoa física
     *
     * @return Nome da pai da pessoa física
     */
    public final String getNomePai() {
        return this.nomePai;
    }

    /**
     * Pais da Nacionalidade
     *
     * @return Pais da Nacionalidade
     */
    public final int getPaisNacionalidade() {
        return this.paisNacionalidade;
    }

    /**
     * Uf do Registro Geral
     *
     * @return Uf do Registro Geral
     */
    public final String getUFRG() {
        return this.ufRg;
    }

    /**
     * Data de Expedição do Registro Geral
     *
     * @return Data de Expedio do Registro Geral
     */
    public final java.util.Date getDataExpedicaoRG() {
        if (this.dataExpedicaoRG != null) {
            return (java.util.Date) this.dataExpedicaoRG.clone();
        } else {
            return null;
        }
    }
    
    /**
     * Data de Expedição da Carteira Nacional CIN
     *
     * @return Data de Expedição da Carteira Nacional CIN
     */
    public final java.util.Date getDataExpedicaoCIN() {
        if (this.dataExpedicaoCIN != null) {
            return (java.util.Date) this.dataExpedicaoCIN.clone();
        } else {
            return null;
        }
    }

    /**
     * Data de Vencimento do Passaporte
     *
     * @return Data de Vencimento do Passaporte
     */
    public final java.util.Date getDataVencPassaporte() {
        if (this.dataVencPassaporte != null) {
            return (java.util.Date) this.dataVencPassaporte.clone();
        } else {
            return null;
        }
    }

    /**
     * Orgao de Expedição do Registro Geral
     *
     * @return Orgao de Expedição do Registro Geral
     */
    public final String getOrgaoExpedicaoRG() {
        return this.orgaoRG;
    }

    /**
     * Numero de Matricula Base
     *
     * @return Numero de Matricula Base
     */
    public final long getNumrMatrBase() {
        return this.numrMatrBase;
    }

    /**
     * Sexo
     *
     * @return Sexo
     */
    public final String getSexo() {
        return this.sexo;
    }

    /**
     * Tipo Estado Civil
     *
     * @return Tipo do estado civil - tipo: <b>char</b>
     */
    public final char getTipoEstadoCivil() {
        return this.tipoEstadoCivil;
    }

    /**
     * Tipo Grau de Instrução
     *
     * @return Tipo Grau de Instrução - tipo: <b>int</b>
     */
    public final int getTipoGrauInstrucao() {
        return this.tipoGrauInstrucao;
    }

    /**
     * Grau de Instrução
     *
     * @return Grau de Instrução - tipo: <b>int</b>
     */
    public final int getGrauInstrucao() {
        return this.grauInstrucao;
    }

    /**
     * Código do Municpio de Naturalidade
     *
     * @return Código do Municpio de Naturalidade - tipo: <b>int</b>
     */
    public final int getCodgMuniNatu() {
        return this.codgMuniNatu;
    }

    /**
     * Código do Municpio do Ttulo de Eleitor
     *
     * @return Código do Municpio do Ttulo de Eleitor - tipo: <b>int</b>
     */
    public final int getCodgMuniTitEleitor() {
        return this.codgMuniTitEleitor;
    }

    /**
     * Código do Passaporte
     *
     * @return Código do Passaporte - tipo: <b>int</b>
     */
    public final int getPaisPassaporte() {
        return this.codgPaisPassaporte;
    }

    /**
     * Código do Pas de Naturalidade
     *
     * @return Código do Pas de Naturalidade - tipo: <b>int</b>
     */
    public final int getCodgPaisNatu() {
        return this.codgPaisNatu;
    }
    
    /**
     * Órgao de Expedição do Registro da carteira Nacional - CIN
     *
     * @return Órgao de Expedição do Registro da carteira Nacional - CIN
     */
    public final String getOrgaoExpedicaoCIN() {
        return this.orgaoCIN;
    }
    
    /**
     * Sigla da UF da Carteira - CIN
     *
     * @return Sigla da UF da Carteira - CIN
     */
    public final String getTxtUF() {
        return this.txtUF;
    }
    
    /**
     * Nome da UF da Carteira - CIN
     *
     * @return  Nome da UF da Carteira - CIN
     */
    public final String getTxtNomeUF() {
        return this.txtNomeUF;
    }
    
    /**
     * Código do Município da Carteira - CIN
     *
     * @return Código do Município da Carteira - CIN
     */
    public final String getCodMunicipio() {
        return this.codMunicipio;
    }
    
    /**
     * Nome do Município da Carteira - CIN
     *
     * @return Nome do Município da Carteira - CIN
     */
    public final String getNomeMunicipio() {
        return this.nomeMunicipio;
    }

    /**
     * Ano de chegada no Brasil
     *
     * @return Ano de chegada no Brasil - tipo: <b>int</b>
     */
    public final int getAnoChegadaBrasil() {
        return this.anoChegadaBrasil;
    }

    public final String getCidadeEstadoEquivNasc() {
        return cidadeEstadoEquivNasc;
    }

    public final int getCodgPaisCasamento() {
        return codgPaisCasamento;
    }

    public final int getCodgMuniCasamento() {
        return codgMuniCasamento;
    }

    public final String getCidadeEstadoEquivCasamento() {
        return cidadeEstadoEquivCasamento;
    }

    public final String getNumrCertCasamento() {
        return numrCertCasamento;
    }

    public final String getLivroCertCasamento() {
        return livroCertCasamento;
    }

    public final String getFolhaCertCasamento() {
        return folhaCertCasamento;
    }

    public final java.util.Date getDataExpedCertCasamento() {
        if (this.dataExpedCertCasamento != null) {
            return (java.util.Date) dataExpedCertCasamento.clone();
        } else {
            return null;
        }
    }

    public final String getNomeConjuge() {
        return nomeConjuge;
    }

    /**
     * Data de Naturalização
     *
     * @return Data de Naturalização
     */
    public final java.util.Date getDataNaturalizacao() {
        if (this.dataNaturalizacao != null) {
            return (java.util.Date) this.dataNaturalizacao.clone();
        } else {
            return null;
        }
    }
    
    /**
     * Seta data do óbito
     *
     * @param dataObito data do óbito
     */
    public final void setDataObito(java.util.Date dataObito) {
        if (dataObito == null) {
            this.dataObito = null;
        } else {
            this.dataObito = (java.util.Date) dataObito.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataObito = true;
    }

    /**
     * Seta o numero da CNH
     *
     * @param numrCNH o nmero da CNH - tipo: <b>long</b>
     */
    public final void setNumrCNH(long numrCNH) {
        if (!(emInclusao || emAlteracao)) {
            this.numrCNH = numrCNH;
            return;
        }
        this.altNumrCNH = true;
        this.numrCNH = numrCNH;
    }

    /**
     * Seta a UF da CNH
     *
     * @param ufCnh a UF da CNH - tipo: <b>String</b>
     */
    public final void setUFCNH(String ufCnh) {
        if (!(emInclusao || emAlteracao)) {
            this.ufCnh = ufCnh;
            return;
        }
        this.altUFCNH = true;
        this.ufCnh = ufCnh;
    }

    /**
     * Seta o numero do passaporte
     *
     * @param numrPassaporte o nmero do passaporte - tipo: <b>String</b>
     */
    public final void setNumrPassaporte(String numrPassaporte) {
        if (!(emInclusao || emAlteracao)) {
            this.numrPassaporte = numrPassaporte;
            return;
        }
        this.altNumrPassaporte = true;
        this.numrPassaporte = numrPassaporte;
    }

    /**
     * Seta o tipo categoria da CNH
     *
     * @param tipoCategCNH o tipo categoria da CNH - tipo: <b>String</b>
     */
    public final void setTipoCategCNH(String tipoCategCNH) {
        if (!(emInclusao || emAlteracao)) {
            this.tipoCategCNH = tipoCategCNH;
            return;
        }
        this.altTipoCategCNH = true;
        this.tipoCategCNH = tipoCategCNH;
    }

    /**
     * Seta o tipo sanguíneo
     *
     * @param tipoSanguineo o tipo sanguíneo - tipo: <b>String</b>
     */
    public final void setTipoSanguineo(String tipoSanguineo) {
        if (!(emInclusao || emAlteracao)) {
            this.tipoSanguineo = tipoSanguineo;
            return;
        }
        this.altTipoSanguineo = true;
        this.tipoSanguineo = tipoSanguineo;
    }

    /**
     * Seta o fator RH
     *
     * @param tipoFatorRh o fator RH - tipo: <b>char</b>
     */
    public final void setFatorRH(char tipoFatorRh) {
        if (!(emInclusao || emAlteracao)) {
            this.tipoFatorRh = tipoFatorRh;
            return;
        }
        this.altTipoFatorRh = true;
        this.tipoFatorRh = tipoFatorRh;
    }

    /**
     * Seta o indicador Doador
     *
     * @param indicadorDoador o indicador Doador - tipo: <b>char</b>
     */
    public final void setIndicadorDoador(char indicadorDoador) {
        if (!(emInclusao || emAlteracao)) {
            this.indicadorDoador = indicadorDoador;
            return;
        }
        this.altIndicadorDoador = true;
        this.indicadorDoador = indicadorDoador;
    }

    /**
     * Seta a data de expedição da CNH
     *
     * @param dataExpedCNH a data de expedio da CNH - tipo:
     * <b>java.util.Date</b>
     */
    public final void setDataExpedCNH(java.util.Date dataExpedCNH) {
        if (dataExpedCNH == null) {
            this.dataExpedCNH = null;
        } else {
            this.dataExpedCNH = (java.util.Date) dataExpedCNH.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataExpedCNH = true;
    }

    /**
     * Seta a data de validade da CNH
     *
     * @param dataValidCNH a data de validade da CNH - tipo:
     * <b>java.util.Date</b>
     */
    public final void setDataValidCNH(java.util.Date dataValidCNH) {
        if (dataValidCNH == null) {
            this.dataValidCNH = null;
        } else {
            this.dataValidCNH = (java.util.Date) dataValidCNH.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataValidCNH = true;
    }

    /**
     * Seta o número do título do eleitor
     *
     * @param numrTitEleitor o número do título do eleitor - tipo: <b>String</b>
     */
    public final void setNumrTitEleitor(String numrTitEleitor) {
        if (!(emInclusao || emAlteracao)) {
            this.numrTitEleitor = numrTitEleitor;
            return;
        }
        this.altNumrTitEleitor = true;
        this.numrTitEleitor = numrTitEleitor;
    }

    /**
     * Seta a zona eleitoral
     *
     * @param zonaEleitor a zona eleitoral - tipo: <b>String</b>
     */
    public final void setZonaEleitor(String zonaEleitor) {
        if (!(emInclusao || emAlteracao)) {
            this.zonaEleitor = zonaEleitor;
            return;
        }
        this.altZonaEleitor = true;
        this.zonaEleitor = zonaEleitor;
    }

    /**
     * Seta a seção eleitoral
     *
     * @param secaoEleitor a seção eleitoral - tipo: <b>String</b>
     */
    public final void setSecaoEleitor(String secaoEleitor) {
        if (!(emInclusao || emAlteracao)) {
            this.secaoEleitor = secaoEleitor;
            return;
        }
        this.altSecaoEleitor = true;
        this.secaoEleitor = secaoEleitor;
    }

    /**
     * Seta a data de expedição do título do eleitor
     *
     * @param dataExpedTitEleitor a data de expedição do título do eleitor -
     * tipo: <b>java.util.Date</b>
     */
    public final void setDataExpedTitEleitor(java.util.Date dataExpedTitEleitor) {
        if (dataExpedTitEleitor == null) {
            this.dataExpedTitEleitor = null;
        } else {
            this.dataExpedTitEleitor = (java.util.Date) dataExpedTitEleitor.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataExpedTitEleitor = true;
    }

    /**
     * Seta a data de vencimento do passaporte
     *
     * @param dataVencPassaporte a data de vencimento do passaporte - tipo:
     * <b>java.util.Date</b>
     */
    public final void setDataVencPassaporte(java.util.Date dataVencPassaporte) {
        if (dataVencPassaporte == null) {
            this.dataVencPassaporte = null;
        } else {
            this.dataVencPassaporte = (java.util.Date) dataVencPassaporte.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataVencPassaporte = true;
    }

    /**
     * Seta o status do serviço militar
     *
     * @param statServMilit o status do serviço militar - tipo: <b>char</b>
     */
    public final void setStatServMilit(char statServMilit) {
        if (!(emInclusao || emAlteracao)) {
            this.statServMilit = statServMilit;
            return;
        }
        this.altStatServMilit = true;
        this.statServMilit = statServMilit;
    }

    /**
     * Seta o numero do certificado do servio militar
     *
     * @param numrCertMilitar o nmero do certificado do servio militar - tipo:
     * <b>String</b>
     */
    public final void setNumrCertMilitar(String numrCertMilitar) {
        if (!(emInclusao || emAlteracao)) {
            this.numrCertMilitar = numrCertMilitar;
            return;
        }
        this.altNumrCertMilitar = true;
        this.numrCertMilitar = numrCertMilitar;
    }

    /**
     * Seta a sigla da fora militar
     *
     * @param siglForcaMilitar a sigla da fora militar - tipo: <b>String</b>
     */
    public final void setSiglForcaMilitar(String siglForcaMilitar) {
        if (!(emInclusao || emAlteracao)) {
            this.siglForcaMilitar = siglForcaMilitar;
            return;
        }
        this.altSiglForcaMilitar = true;
        this.siglForcaMilitar = siglForcaMilitar;
    }

    /**
     * Seta o código da região militar
     *
     * @param codgRegiaoMilitar o código da região militar - tipo: <b>String</b>
     */
    public final void setCodgRegiaoMilitar(String codgRegiaoMilitar) {
        if (!(emInclusao || emAlteracao)) {
            this.codgRegiaoMilitar = codgRegiaoMilitar;
            return;
        }
        this.altCodgRegiaoMilitar = true;
        this.codgRegiaoMilitar = codgRegiaoMilitar;
    }

    /**
     * Seta o número da certidão de óbito
     *
     * @param numrCertObito o número da certidão de óbito - tipo: <b>String</b>
     */
    public final void setNumrCertObito(String numrCertObito) {
        if (!(emInclusao || emAlteracao)) {
            this.numrCertObito = numrCertObito;
            return;
        }
        this.altNumrCertObito = true;
        this.numrCertObito = numrCertObito;
    }

    /**
     * Seta o cartório da certidão de óbito
     *
     * @param cartorioCertObito o cartório da certidão de óbito de tito - tipo:
     * <b>String</b>
     */
    public final void setCartorioCertObito(String cartorioCertObito) {
        if (!(emInclusao || emAlteracao)) {
            this.cartorioCertObito = cartorioCertObito;
            return;
        }
        this.altCartorioCertObito = true;
        this.cartorioCertObito = cartorioCertObito;
    }

    /**
     * Seta o livro da certidão de óbito
     *
     * @param livroCertObito o livro da certidão de óbito - tipo: <b>String</b>
     */
    public final void setLivroCertObito(String livroCertObito) {
        if (!(emInclusao || emAlteracao)) {
            this.livroCertObito = livroCertObito;
            return;
        }
        this.altLivroCertObito = true;
        this.livroCertObito = livroCertObito;
    }

    /**
     * Seta a folha da certidão de óbito
     *
     * @param folhaCertObito a folha da certidão de óbito - tipo: <b>String</b>
     */
    public final void setFolhaCertObito(String folhaCertObito) {
        if (!(emInclusao || emAlteracao)) {
            this.folhaCertObito = folhaCertObito;
            return;
        }
        this.altFolhaCertObito = true;
        this.folhaCertObito = folhaCertObito;
    }

    /**
     * Seta a data de expedição da certidão de óbito
     *
     * @param dataExpedCertObito a data de expedição da certidão de óbito -
     * tipo: <b>java.util.Date</b>
     */
    public final void setDataExpedCertObito(java.util.Date dataExpedCertObito) {
        if (dataExpedCertObito == null) {
            this.dataExpedCertObito = null;
        } else {
            this.dataExpedCertObito = (java.util.Date) dataExpedCertObito.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataExpedCertObito = true;
    }

    /**
     * Seta o código do municpio de óbito
     *
     * @param codgMunicObito o código do municpio de óbito - tipo:
     * <b>String</b>
     */
    public final void setCodgMunicObito(int codgMunicObito) {
        if (!(emInclusao || emAlteracao)) {
            this.codgMunicObito = codgMunicObito;
            return;
        }
        this.altCodgMunicObito = true;
        this.codgMunicObito = codgMunicObito;
    }

    /**
     * Seta a descrição da causa mortis
     *
     * @param descCausaMortis a descrição da causa mortis - tipo: <b>String</b>
     */
    public final void setDescCausaMortis(String descCausaMortis) {
        if (!(emInclusao || emAlteracao)) {
            this.descCausaMortis = descCausaMortis;
            return;
        }
        this.altDescCausaMortis = true;
        this.descCausaMortis = descCausaMortis;
    }

    /**
     * Seta o número da certidão de nascimento
     *
     * @param numrCertNasc úmero da certidão de nascimento - tipo:
     * <b>String</b>
     */
    public final void setNumrCertNasc(String numrCertNasc) {
        if (!(emInclusao || emAlteracao)) {
            this.numrCertNasc = numrCertNasc;
            return;
        }
        this.altNumrCertNasc = true;
        this.numrCertNasc = numrCertNasc;
    }

    /**
     * Seta o cartório da certidão de nascimento
     *
     * @param cartorioCertNasc cartório da certidão de nascimento - tipo:
     * <b>String</b>
     */
    public final void setCartorioCertNasc(String cartorioCertNasc) {
        if (!(emInclusao || emAlteracao)) {
            this.cartorioCertNasc = cartorioCertNasc;
            return;
        }
        this.altCartorioCertNasc = true;
        this.cartorioCertNasc = cartorioCertNasc;
    }

    /**
     * Seta o cartório da certidão de Casamento/Divrcio/Desquite/Separao
     * Judicial/bito do Cnjuge
     *
     * @param cartorioCertCasamento cartório da certidão - tipo: <b>String</b>
     */
    public final void setCartorioCertCasamento(String cartorioCertCasamento) {
        if (!(emInclusao || emAlteracao)) {
            this.cartorioCertCasamento = cartorioCertCasamento;
            return;
        }
        this.altCartorioCertCasamento = true;
        this.cartorioCertCasamento = cartorioCertCasamento;
    }

    public final String getCartorioCertCasamento() {
        return this.cartorioCertCasamento;
    }

    /**
     * Seta o livro da certidão de nascimento
     *
     * @param livroCertNasc livro da certidão de nascimento - tipo:
     * <b>String</b>
     */
    public final void setLivroCertNasc(String livroCertNasc) {
        if (!(emInclusao || emAlteracao)) {
            this.livroCertNasc = livroCertNasc;
            return;
        }
        this.altLivroCertNasc = true;
        this.livroCertNasc = livroCertNasc;
    }

    /**
     * Seta a folha da certidão de nascimento
     *
     * @param folhaCertNasc folha da certidão de nascimento - tipo:
     * <b>String</b>
     */
    public final void setFolhaCertNasc(String folhaCertNasc) {
        if (!(emInclusao || emAlteracao)) {
            this.folhaCertNasc = folhaCertNasc;
            return;
        }
        this.altFolhaCertNasc = true;
        this.folhaCertNasc = folhaCertNasc;
    }

    /**
     * Seta a data de expedição da certidão de nascimento
     *
     * @param dataExpedCertNasc data de expedição da certidão de nascimento -
     * tipo: <b>Date</b>
     */
    public final void setDataExpedCertNasc(java.util.Date dataExpedCertNasc) {
        if (dataExpedCertNasc == null) {
            this.dataExpedCertNasc = null;
        } else {
            this.dataExpedCertNasc = (java.util.Date) dataExpedCertNasc.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataExpedCertNasc = true;
    }

    /**
     * Seta o código do municpio da certidão de nascimento
     *
     * @param codgMuniCertNasc código do municpio da certidão de nascimento -
     * tipo: <b>int</b>
     */
    public final void setCodgMuniCertNasc(int codgMuniCertNasc) {
        if (!(emInclusao || emAlteracao)) {
            this.codgMuniCertNasc = codgMuniCertNasc;
            return;
        }
        this.altCodgMuniCertNasc = true;
        this.codgMuniCertNasc = codgMuniCertNasc;
    }

    /**
     * Seta nome da pessoa
     *
     * @param nome nome da pessoa
     */
    public final void setNome(String nome) {
        if (!(emInclusao || emAlteracao)) {
            this.nome = nome;
            return;
        }
        this.altNome = true;
        this.nome = Pessoa.tratarNome(nome);
    }

    /**
     * Seta CPF
     *
     * @param numrCPF CPF
     */
    public final void setCPF(long numrCPF) {
        if (!(emInclusao || emAlteracao)) {
            this.numrCPF = numrCPF;
            return;
        }
        this.altNumrCPF = true;
        this.numrCPF = numrCPF;
    }

    /**
     * Seta Data de Nascimento
     *
     * @param dataNascimento Data de Nascimento
     */
    public final void setDataNascimento(java.util.Date dataNascimento) {
        if (dataNascimento == null) {
            this.dataNascimento = null;
        } else {
            this.dataNascimento = (java.util.Date) dataNascimento.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataNascimento = true;
    }

    /**
     * Seta Numero da identidade da pessoa física
     *
     * @param numrRG Numero da identidade da pessoa física
     */
    public final void setnumrRG(String numrRG) {
        if (!(emInclusao || emAlteracao)) {
            this.numrRG = numrRG;
            return;
        }
        this.altNumrRG = true;
        this.numrRG = numrRG;
    }

    /**
     * Seta Nome da Mãe da pessoa física
     *
     * @param nomeMae Nome da Mãe da pessoa física
     */
    public final void setNomeMae(String nomeMae) {
        if (!(emInclusao || emAlteracao)) {
            this.nomeMae = nomeMae;
            return;
        }
        this.altNomeMae = true;
        this.nomeMae = Pessoa.tratarNome(nomeMae);
    }

    /**
     * Seta Nome do pai da pessoa física
     *
     * @param nomePai Nome da pai da pessoa física
     */
    public final void setNomePai(String nomePai) {
        if (!(emInclusao || emAlteracao)) {
            this.nomePai = nomePai;
            return;
        }
        this.altNomePai = true;
        this.nomePai = Pessoa.tratarNome(nomePai);
    }

    /**
     * Seta Pais da Nacionalidade
     *
     * @param paisNacionalidade Pais da Nacionalidade
     */
    public final void setPaisNacionalidade(int paisNacionalidade) {
        if (!(emInclusao || emAlteracao)) {
            this.paisNacionalidade = paisNacionalidade;
            return;
        }
        this.altPaisNacionalidade = true;
        this.paisNacionalidade = paisNacionalidade;
    }

    /**
     * Seta Uf do Registro Geral
     *
     * @param ufRg Uf do Registro Geral
     */
    public final void setUFRG(String ufRg) {
        if (!(emInclusao || emAlteracao)) {
            this.ufRg = ufRg;
            return;
        }
        this.altUFRG = true;
        this.ufRg = ufRg.toUpperCase();
    }

    /**
     * Seta Data de Expedição do Registro Geral - RG
     *
     * @param dataExpedicaoRG Seta Data de Expedição do Registro Geral - RG
     */
    public final void setDataExpedicaoRG(java.util.Date dataExpedicaoRG) {
        if (dataExpedicaoRG == null) {
            this.dataExpedicaoRG = null;
        } else {
            this.dataExpedicaoRG = (java.util.Date) dataExpedicaoRG.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altExpedicaoRG = true;
    }
    
    
    /**
     * Seta Data de Expedição do Registro de Carteira Nacional - CIN
     *
     * @param dataExpedicaoCIN Seta Data de Expedição do Registro de Carteira Nacional - CIN
     */
    public final void setDataExpedicaoCIN(java.util.Date dataExpedicaoCIN) {
        if (dataExpedicaoCIN == null) {
            this.dataExpedicaoCIN = null;
        } else {
            this.dataExpedicaoCIN = (java.util.Date) dataExpedicaoCIN.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataExpedicaoCIN = true;
    }
    

    /**
     * Seta ID
     *
     * @param idPessoaFisica ID
     */
    public final void setId(long idPessoaFisica) {
        this.iden = idPessoaFisica;
    }

    /**
     * Seta Orgo de Expedio do Registro Geral
     *
     * @param orgaoRG Orgo de Expedio do Registro Geral
     */
    public final void setOrgaoExpedicaoRG(String orgaoRG) {
        if (!(emInclusao || emAlteracao)) {
            this.orgaoRG = orgaoRG;
            return;
        }
        this.altOrgaoRG = true;
        this.orgaoRG = orgaoRG.toUpperCase();
    }

    /**
     * Seta Nmero de Matricula Base
     *
     * @param numrMatrBase Nmero de Matricula Base
     */
    public final void setNumrMatrBase(long numrMatrBase) {
        if (!(emInclusao || emAlteracao)) {
            this.numrMatrBase = numrMatrBase;
            return;
        }
        this.altNumrMatrBase = true;
        this.numrMatrBase = numrMatrBase;
    }

    /**
     * Seta Sexo
     *
     * @param sexo Sexo
     */
    public final void setSexo(String sexo) {
        if (!(emInclusao || emAlteracao)) {
            this.sexo = sexo;
            return;
        }
        this.altSexo = true;
        this.sexo = sexo;
    }

    /**
     * Seta Tipo Estado Civil
     *
     * @param tipoEstadoCivil Tipo Estado Civil - tipo: <b>char</b>
     */
    public final void setTipoEstadoCivil(char tipoEstadoCivil) {
        if (!(emInclusao || emAlteracao)) {
            this.tipoEstadoCivil = tipoEstadoCivil;
            return;
        }
        this.altTipoEstadoCivil = true;
        this.tipoEstadoCivil = tipoEstadoCivil;
    }

    /**
     * Seta Tipo Grau de Instrução
     *
     * @param tipoGrauInstrucao Tipo Grau de Instrução - tipo: <b>int</b>
     */
    public final void setTipoGrauInstrucao(int tipoGrauInstrucao) {
        if (!(emInclusao || emAlteracao)) {
            this.tipoGrauInstrucao = tipoGrauInstrucao;
            return;
        }
        this.altTipoGrauInstrucao = true;
        this.tipoGrauInstrucao = tipoGrauInstrucao;
    }

    /**
     * Seta Grau de Instrução
     *
     * @param grauInstrucao Grau de Instrução - tipo: <b>int</b>
     */
    public final void setGrauInstrucao(int grauInstrucao) {
        if (!(emInclusao || emAlteracao)) {
            this.grauInstrucao = grauInstrucao;
            return;
        }
        this.altGrauInstrucao = true;
        this.grauInstrucao = grauInstrucao;
    }

    /**
     * Seta Código do Municpio de Naturalidade
     *
     * @param codgMuniNatu Código do Municpio de Naturalidade - tipo:
     * <b>int</b>
     */
    public final void setCodgMuniNatu(int codgMuniNatu) {
        if (!(emInclusao || emAlteracao)) {
            this.codgMuniNatu = codgMuniNatu;
            return;
        }
        this.altCodgMuniNatu = true;
        this.codgMuniNatu = codgMuniNatu;
    }

    /**
     * Seta Código do Municpio do Título de Eleitor
     *
     * @param codgMuniTitEleitor Código do Municpio do Título de Eleitor -
     * tipo: <b>int</b>
     */
    public final void setCodgMuniTitEleitor(int codgMuniTitEleitor) {
        if (!(emInclusao || emAlteracao)) {
            this.codgMuniTitEleitor = codgMuniTitEleitor;
            return;
        }
        this.altCodgMuniTitEleitor = true;
        this.codgMuniTitEleitor = codgMuniTitEleitor;
    }

    /**
     * Seta Código do Pas de Passaporte
     *
     * @param codgPaisPassaporte Código do Pas de Passaporte - tipo: <b>int</b>
     */
    public final void setPaisPassaporte(int codgPaisPassaporte) {
        if (!(emInclusao || emAlteracao)) {
            this.codgPaisPassaporte = codgPaisPassaporte;
            return;
        }
        this.altCodgPaisPassaporte = true;
        this.codgPaisPassaporte = codgPaisPassaporte;
    }

    /**
     * Seta Código do Pais de Naturalidade
     *
     * @param codgPaisNatu Código do Pais de Naturalidade - tipo: <b>int</b>
     */
    public final void setCodgPaisNatu(int codgPaisNatu) {
        if (!(emInclusao || emAlteracao)) {
            this.codgPaisNatu = codgPaisNatu;
            return;
        }
        this.altCodgPaisNatu = true;
        this.codgPaisNatu = codgPaisNatu;
    }
    
    /**
     * Seta Órgão de Expedição da Carteira Nacional - CIN
     *
     * @param orgaoCIN Seta Órgão de Expedição da Carteira Nacional - CIN
     */
    public final void setOrgaoExpedicaoCIN(String orgaoCIN) {
        if (!(emInclusao || emAlteracao)) {
            this.orgaoCIN = orgaoCIN;
            return;
        }
        this.altOrgaoCIN = true;
        this.orgaoCIN = orgaoCIN.toUpperCase();
    }
    
    /**
     * Seta a Sigla da UF da Carteira Nacional - CIN
     *
     * @param txtUF Seta a Sigla da UF da Carteira Nacional - CIN
     */
    public final void setTxtUFCIN(String txtUF) {
        if (!(emInclusao || emAlteracao)) {
            this.txtUF = txtUF;
            return;
        }
        this.altTxtUF = true;
        this.txtUF = txtUF.toUpperCase();
    }
    
    /**
     * Seta o Nome da UF da Carteira Nacional - CIN
     *
     * @param txtNomeUF Seta o Nome da UF da Carteira Nacional - CIN
     */
    public final void setTxtNomeUF(String txtNomeUF) {
        if (!(emInclusao || emAlteracao)) {
            this.txtNomeUF = txtNomeUF;
            return;
        }
        this.altTxtNomeUF = true;
        this.txtNomeUF = txtNomeUF.toUpperCase();
    }
    
    /**
     * Seta Código do Município da Carteira - CIN
     *
     * @param codMunicipio Seta Código do Município da Carteira - CIN
     */
    public final void setCodMunicipio(String codMunicipio) {
        if (!(emInclusao || emAlteracao)) {
            this.codMunicipio = codMunicipio;
            return;
        }
        this.altCodMunicipio = true;
        this.codMunicipio = codMunicipio;
    }
    
    /**
     * Seta o Nome do Município da Carteira Nacional - CIN
     *
     * @param nomeMunicipio Seta o Nome do Município da Carteira Nacional - CIN
     */
    public final void setNomeMunicipio(String nomeMunicipio) {
        if (!(emInclusao || emAlteracao)) {
            this.nomeMunicipio = nomeMunicipio;
            return;
        }
        this.altNomeMunicipio = true;
        this.nomeMunicipio = nomeMunicipio.toUpperCase();
    }
    

    /**
     * Seta Ano da chegada no Brasil
     *
     * @param anoChegadaBrasil Ano da chegada no Brasil - tipo: <b>int</b>
     */
    public final void setAnoChegadaBrasil(int anoChegadaBrasil) {
        if (!(emInclusao || emAlteracao)) {
            this.anoChegadaBrasil = anoChegadaBrasil;
            return;
        }
        this.altAnoChegadaBrasil = true;
        this.anoChegadaBrasil = anoChegadaBrasil;
    }

    public final void setCidadeEstadoEquivNasc(String cidadeEstadoEquivNasc) {
        if (!(emInclusao || emAlteracao)) {
            this.cidadeEstadoEquivNasc = cidadeEstadoEquivNasc;
            return;
        }
        this.altCidadeEstadoEquivNasc = true;
        this.cidadeEstadoEquivNasc = cidadeEstadoEquivNasc;
    }

    public final void setCodgPaisCasamento(int paisCasamento) {
        if (!(emInclusao || emAlteracao)) {
            this.codgPaisCasamento = paisCasamento;
            return;
        }
        this.altPaisCasamento = true;
        this.codgPaisCasamento = paisCasamento;
    }

    public final void setCodgMuniCasamento(int codgMuniCasamento) {
        if (!(emInclusao || emAlteracao)) {
            this.codgMuniCasamento = codgMuniCasamento;
            return;
        }
        this.altCodgMuniCasamento = true;
        this.codgMuniCasamento = codgMuniCasamento;
    }

    public final void setCidadeEstadoEquivCasamento(String cidadeEstadoEquivCasamento) {
        if (!(emInclusao || emAlteracao)) {
            this.cidadeEstadoEquivCasamento = cidadeEstadoEquivCasamento;
            return;
        }
        this.altCidadeEstadoEquivCasamento = true;
        this.cidadeEstadoEquivCasamento = cidadeEstadoEquivCasamento;
    }

    public final void setNumrCertCasamento(String numrCertCasamento) {
        if (!(emInclusao || emAlteracao)) {
            this.numrCertCasamento = numrCertCasamento;
            return;
        }
        this.altNumrCertCasamento = true;
        this.numrCertCasamento = numrCertCasamento;
    }

    public final void setLivroCertCasamento(String livroCertCasamento) {
        if (!(emInclusao || emAlteracao)) {
            this.livroCertCasamento = livroCertCasamento;
            return;
        }
        this.altLivroCertCasamento = true;
        this.livroCertCasamento = livroCertCasamento;
    }

    public final void setFolhaCertCasamento(String folhaCertCasamento) {
        if (!(emInclusao || emAlteracao)) {
            this.folhaCertCasamento = folhaCertCasamento;
            return;
        }
        this.altFolhaCertCasamento = true;
        this.folhaCertCasamento = folhaCertCasamento;
    }

    public final void setDataExpedCertCasamento(java.util.Date dataExpedCertCasamento) {
        if (dataExpedCertCasamento == null) {
            this.dataExpedCertCasamento = null;
        } else {
            this.dataExpedCertCasamento = (java.util.Date) dataExpedCertCasamento.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataExpedCertCasamento = true;
    }

    public final void setNomeConjuge(String nomeConjuge) {
        if (!(emInclusao || emAlteracao)) {
            this.nomeConjuge = nomeConjuge;
            return;
        }
        this.altNomeConjuge = true;
        this.nomeConjuge = nomeConjuge;
    }

    /**
     * Seta Data de Naturalizao
     *
     * @param dataNaturalizacao Data de Naturalizacao
     */
    public final void setDataNaturalizacao(java.util.Date dataNaturalizacao) {
        if (dataNaturalizacao == null) {
            this.dataNaturalizacao = null;
        } else {
            this.dataNaturalizacao = (java.util.Date) dataNaturalizacao.clone();
        }
        if (!(emInclusao || emAlteracao)) {
            return;
        }
        this.altDataNaturalizacao = true;
    }

    
    /**
     * Atualiza atributos de falecimento.
     *
     * @param codServidor - codigo do servidor
     * @param numRegObito - registro do bito
     * @param nomeCartorioObito - nome do cartrio
     * @param livroObito - livro de registro
     * @param folhaLivroObito - folha de registro
     * @param dataObito - data do bito
     * @param dataExpedCertObito - data da expedio do bito
     * @param codgMunicObito - cdigo do muncipio do bito
     * @param descCausaMortis - descrio da causa do bito
     * @return True ou False caso a atualizao seja bem sucedida ou no. Quando
     * o resultado for false os seguintes mtodos podero ser usados:
     * getErroMensagem() - mensagem que especifica o problema encontrado
     * getErroCodigo() - cdigo do problema encontrado
     */
    public final boolean atualizarAtributosFalecimento(long codServidor,
            String numRegObito,
            String nomeCartorioObito,
            String livroObito,
            String folhaLivroObito,
            java.util.Date dataObito,
            java.util.Date dataExpedCertObito,
            int codgMunicObito,
            String descCausaMortis) {

        sql = "UPDATE PESSOAS_FISICAS_COMPLEMENTO "
                + "SET PFIC_NUMR_CERT_OBITO = ?, "
                + "PFIC_INFO_CARTORIO_CERT_OBITO = ?, "
                + "PFIC_INFO_LIVRO_CERT_OBITO = ?, "
                + "PFIC_INFO_FOLHA_CERT_OBITO = ?, "
                + "PFIC_DATA_OBITO = TO_DATE(?, 'DDMMYYYY'), "
                + "PFIC_DATA_EXPED_CERT_OBITO = TO_DATE(?, 'DDMMYYYY'), "
                + "PFIC_MUNI_CODG_OBITO = ?, "
                + "PFIC_DESC_CAUSA_MORTIS = ? "
                + "WHERE PFIC_PFIS_IDEN = ?";

        PreparedStatement pstmt = getObjTransacao().prepararSQL(sql);
        if (pstmt == null) {
            erroCodigo = getObjTransacao().getErroCodigo();
            erroMensagem = getObjTransacao().getErroMensagem();
            return false;
        }

        try {

            if (numRegObito == null) {
                pstmt.setNull(1, Types.VARCHAR);
            } else {
                pstmt.setString(1, numRegObito);
            }

            if (nomeCartorioObito == null) {
                pstmt.setNull(2, Types.VARCHAR);
            } else {
                pstmt.setString(2, nomeCartorioObito);
            }

            if (livroObito == null) {
                pstmt.setNull(3, Types.VARCHAR);
            } else {
                pstmt.setString(3, livroObito);
            }

            if (folhaLivroObito == null) {
                pstmt.setNull(4, Types.VARCHAR);
            } else {
                pstmt.setString(4, folhaLivroObito);
            }

            if (dataObito == null) {
                pstmt.setNull(5, Types.VARCHAR);
            } else {
                pstmt.setString(5, sdf.format(dataObito));
            }

            if (dataExpedCertObito == null) {
                pstmt.setNull(6, Types.VARCHAR);
            } else {
                pstmt.setString(6, sdf.format(dataExpedCertObito));
            }

            if (codgMunicObito == 0) {
                pstmt.setNull(7, Types.INTEGER);
            } else {
                pstmt.setInt(7, codgMunicObito);
            }

            if (descCausaMortis == null) {
                pstmt.setNull(8, Types.INTEGER);
            } else {
                pstmt.setString(8, descCausaMortis);
            }

            if (descCausaMortis == null) {
                pstmt.setNull(9, Types.INTEGER);
            } else {
                pstmt.setString(9, matriculaCertObito);
            }
            
            pstmt.setLong(10, codServidor);

            getObjTransacao().autenticarUsuarioPortal();
            if (getObjTransacao().executarSQL(pstmt) == 0) {
                erroCodigo = getObjTransacao().getErroCodigo();
                erroMensagem = getObjTransacao().getErroMensagem();
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            erroCodigo = getObjTransacao().getErroCodigo();
            erroMensagem = getObjTransacao().getErroMensagem();
            return false;
        }
    }

    /**
     * Consulta os tipos de sexos possveis na tabela DOMINIOS atravs do
     * domnio SEXO.<br>
     *
     * @return <b><i>ResultSet</i></b>: se consulta realizada com sucesso,
     * retornando os tipos de sexos possveis. Esse ResultSet contm os
     * seguintes campos: <br> <ul type="square"><li><b>SIGNIFICADO</b> -
     * significado do domnio - tipo: <b>String</b></li></ul><br> <ul
     * type="square"><li><b>VALOR_INICIAL</b> - valor inicial do domnio - tipo:
     * <b>String</b></li></ul><br>
     * @return <b><i>null</i></b>: se ocorreu algum erro, os seguintes mtodos
     * podero ser usados: <ul type="square"><li> <b>getErroMensagem()</b> -
     * mensagem que especifica o problema encontrado.</li></ul> <ul
     * type="square"><li><b> getErroCodigo()</b> - cdigo do problema
     * encontrado.</li></ul>
     */
    public final ResultSet getSexos() {
        Dominio dominio = new Dominio(getObjTransacao());
        try {
            ResultSet objRS = dominio.getDominio("SEXO");
            if (objRS == null) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return null;
            } else {
                return objRS;
            }
        } catch (SQLException e) {
            erroMensagem = e.getMessage();
            erroCodigo = e.getErrorCode();
            return null;
        }
    }

    /**
     * Consulta os tipos de estados civis possveis na tabela DOMINIOS atravs
     * do domnio ESTADO CIVIL.<br>
     *
     * @return <b><i>ResultSet</i></b>: se consulta realizada com sucesso,
     * retornando os tipos de estados civis possveis. Esse ResultSet contm os
     * seguintes campos: <br> <ul type="square"><li><b>SIGNIFICADO</b> -
     * significado do domnio - tipo: <b>String</b></li></ul><br> <ul
     * type="square"><li><b>VALOR_INICIAL</b> - valor inicial do domnio - tipo:
     * <b>String</b></li></ul><br>
     * @return <b><i>null</i></b>: se ocorreu algum erro, os seguintes mtodos
     * podero ser usados: <ul type="square"><li> <b>getErroMensagem()</b> -
     * mensagem que especifica o problema encontrado.</li></ul> <ul
     * type="square"><li><b> getErroCodigo()</b> - cdigo do problema
     * encontrado.</li></ul>
     */
    public final ResultSet getEstadosCivis() {
        Dominio dominio = new Dominio(getObjTransacao());
        try {
            ResultSet objRS = dominio.getDominio("ESTADO CIVIL");
            if (objRS == null) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return null;
            } else {
                return objRS;
            }
        } catch (SQLException e) {
            erroMensagem = e.getMessage();
            erroCodigo = e.getErrorCode();
            return null;
        }
    }

    /**
     * Consulta os tipos sangneos possveis na tabela DOMINIOS atravs do
     * domnio TIPO SANGUINEO.<br>
     *
     * @return <b><i>ResultSet</i></b>: se consulta realizada com sucesso,
     * retornando os tipos sangneos possveis. Esse ResultSet contm os
     * seguintes campos: <br> <ul type="square"><li><b>SIGNIFICADO</b> -
     * significado do domnio - tipo: <b>String</b></li></ul><br> <ul
     * type="square"><li><b>VALOR_INICIAL</b> - valor inicial do domnio - tipo:
     * <b>String</b></li></ul><br>
     * @return <b><i>null</i></b>: se ocorreu algum erro, os seguintes mtodos
     * podero ser usados: <ul type="square"><li> <b>getErroMensagem()</b> -
     * mensagem que especifica o problema encontrado.</li></ul> <ul
     * type="square"><li><b> getErroCodigo()</b> - cdigo do problema
     * encontrado.</li></ul>
     */
    public final ResultSet getTiposSanguineos() {
        Dominio dominio = new Dominio(getObjTransacao());
        try {
            ResultSet objRS = dominio.getDominio("TIPO SANGUINEO");
            if (objRS == null) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return null;
            } else {
                return objRS;
            }
        } catch (SQLException e) {
            erroMensagem = e.getMessage();
            erroCodigo = e.getErrorCode();
            return null;
        }
    }

    /**
     * Consulta os fatores RH possveis na tabela DOMINIOS atravs do domnio
     * FATOR RH.<br>
     *
     * @return <b><i>ResultSet</i></b>: se consulta realizada com sucesso,
     * retornando os fatores RH possveis. Esse ResultSet contm os seguintes
     * campos: <br> <ul type="square"><li><b>SIGNIFICADO</b> - significado do
     * domnio - tipo: <b>String</b></li></ul><br> <ul
     * type="square"><li><b>VALOR_INICIAL</b> - valor inicial do domnio - tipo:
     * <b>String</b></li></ul><br>
     * @return <b><i>null</i></b>: se ocorreu algum erro, os seguintes mtodos
     * podero ser usados: <ul type="square"><li> <b>getErroMensagem()</b> -
     * mensagem que especifica o problema encontrado.</li></ul> <ul
     * type="square"><li><b> getErroCodigo()</b> - cdigo do problema
     * encontrado.</li></ul>
     */
    public final ResultSet getFatoresRH() {
        Dominio dominio = new Dominio(getObjTransacao());
        try {
            ResultSet objRS = dominio.getDominio("FATOR RH");
            if (objRS == null) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return null;
            } else {
                return objRS;
            }
        } catch (SQLException e) {
            erroMensagem = e.getMessage();
            erroCodigo = e.getErrorCode();
            return null;
        }
    }

    /**
     * Consulta os valores possveis(SIM/NO) para indicador de doador na tabela
     * DOMINIOS atravs do domnio SIM_NAO.<br>
     *
     * @return <b><i>ResultSet</i></b>: se consulta realizada com sucesso,
     * retornando os valores possveis(SIM/NO) para indicador de doador. Esse
     * ResultSet contm os seguintes campos: <br> <ul
     * type="square"><li><b>SIGNIFICADO</b> - significado do domnio - tipo:
     * <b>String</b></li></ul><br> <ul type="square"><li><b>VALOR_INICIAL</b> -
     * valor inicial do domnio - tipo: <b>String</b></li></ul><br>
     * @return <b><i>null</i></b>: se ocorreu algum erro, os seguintes mtodos
     * podero ser usados: <ul type="square"><li> <b>getErroMensagem()</b> -
     * mensagem que especifica o problema encontrado.</li></ul> <ul
     * type="square"><li><b> getErroCodigo()</b> - cdigo do problema
     * encontrado.</li></ul>
     */
    public final ResultSet getIndicadoresDoador() {
        Dominio dominio = new Dominio(getObjTransacao());
        try {
            ResultSet objRS = dominio.getDominio("SIM_NAO");
            if (objRS == null) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return null;
            } else {
                return objRS;
            }
        } catch (SQLException e) {
            erroMensagem = e.getMessage();
            erroCodigo = e.getErrorCode();
            return null;
        }
    }

    /**
     * Calcula a idade da pessoa.<br>
     *
     * @return maior ou igual a <i>0</i> se data de nascimento vlida ou
     * <i>-1</i> se data nascimento invlida.
     */
    public final int getIdade() {

        this.dataNascimento = this.getDataNascimento();
        java.util.Date dataAtual = new java.util.Date();
        int anoNascimento = Integer.parseInt(new SimpleDateFormat("yyyy").format(dataNascimento));
        int mesNascimento = Integer.parseInt(new SimpleDateFormat("MM").format(dataNascimento));
        int diaNascimento = Integer.parseInt(new SimpleDateFormat("dd").format(dataNascimento));
        int anoAtual = Integer.parseInt(new SimpleDateFormat("yyyy").format(dataAtual));
        int mesAtual = Integer.parseInt(new SimpleDateFormat("MM").format(dataAtual));
        int diaAtual = Integer.parseInt(new SimpleDateFormat("dd").format(dataAtual));

        if (anoNascimento < anoAtual) {
            if (mesNascimento < mesAtual) {
                return anoAtual - anoNascimento;
            } else {
                if (mesNascimento == mesAtual) {
                    if (diaNascimento <= diaAtual) {
                        return anoAtual - anoNascimento;
                    } else {
                        return anoAtual - anoNascimento - 1;
                    }
                } else {
                    return anoAtual - anoNascimento - 1;
                }
            }
        } else {
            erroMensagem = "Data de Nascimento invlida. No  possvel calcular a idade da pessoa.";
            return -1;
        }
    }

    /**
     * Verifica a existncia da certido de nascimento. Aps a execuo do
     * mesmo, faz-se necessrio o uso dos mtodos 'gets' para se obter os
     * valores dos atributos setados pelo mtodo.
     *
     * @param cartorioCertNasc o identificador do servidor
     * @param codgMuniCertNasc
     * @param numrCertNasc
     * @param livroCertNasc
     * @param folhaCertNasc
     * @param dataExpedCertNasc
     * @return <i>true</i> se a consulta realizada com sucesso, e <i>false</i>
     * se houve algum erro. <p> - Quando o resultado for <i>true</i> os
     * seguintes mtodos podero ser usados:</p> <p> getNumrCertNasc() --
     * retorna o nmero da certido de nascimento - tipo: String <p>
     * getCartorioCertNasc() -- retorna o nmero do cartrio da certido de
     * nascimento - tipo: String <p> getLivroCertNasc() -- retorna o livro da
     * certido de nascimento - tipo: String <p> getFolhaCertNasc() -- retorna a
     * folha da certido de nascimento - tipo: String <p> getDataExpedCertNasc()
     * -- retorna a data de expedio da certido de nascimento - tipo:
     * java.util.Date <p> getCodgMuniCertNasc() -- retorna o cdigo do municpio
     * da certido de nascimento - tipo: int
     * @return true se tudo OK e false se algum erro acontecer. <p> - Quando o
     * resultado for <i>false</i> os seguintes mtodos podero ser usados:</p>
     * @return <ul type="square"><li> getErroMensagem() - mensagem que
     * especifica o problema encontrado</li></ul>
     * @return <ul type="square"><li> getErroCodigo() - cdigo do problema
     * encontrado</li></ul>
     */
    public final boolean verificarExistenciaCertNascimento(String cartorioCertNasc,
            int codgMuniCertNasc,
            String numrCertNasc,
            String livroCertNasc,
            String folhaCertNasc,
            java.util.Date dataExpedCertNasc) {

        sql = "SELECT PFIC_NUMR_CERT_NASC, "
                + "PFIC_INFO_CARTORIO_CERT_NASC, "
                + "PFIC_INFO_LIVRO_CERT_NASC, "
                + "PFIC_INFO_FOLHA_CERT_NASC, "
                + "PFIC_DATA_EXPED_CERT_NASC, "
                + "PFIC_MUNI_CODG_CERT_NASC "
                + "FROM PESSOAS_FISICAS_COMPLEMENTO "
                + "WHERE PFIC_NUMR_CERT_NASC = ? "
                + "AND PFIC_INFO_CARTORIO_CERT_NASC = ? "
                + "AND PFIC_INFO_LIVRO_CERT_NASC = ? "
                + "AND PFIC_INFO_FOLHA_CERT_NASC = ? "
                + "AND PFIC_DATA_EXPED_CERT_NASC = ? "
                + "AND PFIC_MUNI_CODG_CERT_NASC = ?";

        ResultSet objRS = null;
        try {
            PreparedStatement pstmt = getObjTransacao().prepararSQL(sql);
            if (pstmt == null) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return false;
            }

            if (numrCertNasc == null || numrCertNasc.equals("")) {
                pstmt.setNull(1, Types.VARCHAR);
            } else {
                pstmt.setString(1, numrCertNasc);
            }

            if (cartorioCertNasc == null || cartorioCertNasc.equals("")) {
                pstmt.setNull(2, Types.VARCHAR);
            } else {
                pstmt.setString(2, cartorioCertNasc);
            }

            if (livroCertNasc == null || livroCertNasc.equals("")) {
                pstmt.setNull(3, Types.VARCHAR);
            } else {
                pstmt.setString(3, livroCertNasc);
            }

            if (folhaCertNasc == null || folhaCertNasc.equals("")) {
                pstmt.setNull(4, Types.VARCHAR);
            } else {
                pstmt.setString(4, folhaCertNasc);
            }

            if (dataExpedCertNasc == null) {
                pstmt.setNull(5, Types.DATE);
            } else {
                pstmt.setString(5, sdf.format(dataExpedCertNasc));
            }

            if (codgMuniCertNasc == 0) {
                pstmt.setNull(6, Types.INTEGER);
            } else {
                pstmt.setInt(6, codgMuniCertNasc);
            }

            objRS = getObjTransacao().consultarSQL(pstmt);
            if (objRS == null || !objRS.next()) {
                erroMensagem = objRS == null ? getObjTransacao().getErroMensagem()
                        : "Certido de nascimento no encontrada.";
                erroCodigo = objRS == null ? getObjTransacao().getErroCodigo()
                        : ERRO_CONSULTA_INVALIDA;
                return false;
            } else {  /*
                 * Se encontrado
                 */
                this.numrCertNasc = numrCertNasc;
                this.cartorioCertNasc = cartorioCertNasc;
                this.livroCertNasc = livroCertNasc;
                this.folhaCertNasc = folhaCertNasc;
                if (dataExpedCertNasc == null) {
                    this.dataExpedCertNasc = null;
                } else {
                    this.dataExpedCertNasc = (java.util.Date) dataExpedCertNasc.clone();
                }
                this.codgMuniCertNasc = codgMuniCertNasc;
                return true;
            }
        } catch (SQLException e) {
            erroMensagem = e.getMessage();
            erroCodigo = e.getErrorCode();
            return false;
        } finally {
            try {
                if (objRS != null) {
                    objRS.close();
                }
            } catch (SQLException ex) {
                Logger.getRootLogger().error("Erro ao fechar ResultSet: " + ex.getMessage());
            }
        }
    }

    /**
     * Verifica a existncia da certido de nascimento. Aps a execuo do
     * mesmo, faz-se necessrio o uso dos mtodos 'gets' para se obter os
     * valores dos atributos setados pelo mtodo.
     *
     * @param cartorioCertNasc o identificador do servidor
     * @param codgMuniCertNasc nome do dependente
     * @param numrCertNasc nome da me do dependente
     * @param livroCertNasc data de nascimento do dependente
     * @param folhaCertNasc data de nascimento do dependente
     * @param dataExpedCertNasc data de nascimento do dependente
     * @param idDependente identificador do dependente
     * @return <i>true</i> se a consulta realizada com sucesso, e <i>false</i>
     * se houve algum erro. <p> - Quando o resultado for <i>true</i> os
     * seguintes mtodos podero ser usados:</p> <p> getNumrCertNasc() --
     * retorna o nmero da certido de nascimento - tipo: String <p>
     * getCartorioCertNasc() -- retorna o nmero do cartrio da certido de
     * nascimento - tipo: String <p> getLivroCertNasc() -- retorna o livro da
     * certido de nascimento - tipo: String <p> getFolhaCertNasc() -- retorna a
     * folha da certido de nascimento - tipo: String <p> getDataExpedCertNasc()
     * -- retorna a data de expedio da certido de nascimento - tipo:
     * java.util.Date <p> getCodgMuniCertNasc() -- retorna o cdigo do municpio
     * da certido de nascimento - tipo: int
     * @return true se tudo OK e false se algum erro acontecer. <p> - Quando o
     * resultado for <i>false</i> os seguintes mtodos podero ser usados:</p>
     * @return <ul type="square"><li> getErroMensagem() - mensagem que
     * especifica o problema encontrado</li></ul>
     * @return <ul type="square"><li> getErroCodigo() - cdigo do problema
     * encontrado</li></ul>
     */
    public final boolean verificarExistenciaCertNascimento(String cartorioCertNasc,
            int codgMuniCertNasc,
            String numrCertNasc,
            String livroCertNasc,
            String folhaCertNasc,
            java.util.Date dataExpedCertNasc,
            long idDependente) {

        sql = "SELECT PFIC_NUMR_CERT_NASC, "
                + "PFIC_INFO_CARTORIO_CERT_NASC, "
                + "PFIC_INFO_LIVRO_CERT_NASC, "
                + "PFIC_INFO_FOLHA_CERT_NASC, "
                + "PFIC_DATA_EXPED_CERT_NASC, "
                + "PFIC_MUNI_CODG_CERT_NASC "
                + "FROM PESSOAS_FISICAS_COMPLEMENTO "
                + "WHERE PFIC_NUMR_CERT_NASC = ? "
                + "AND PFIC_INFO_CARTORIO_CERT_NASC = ? "
                + "AND PFIC_INFO_LIVRO_CERT_NASC = ? "
                + "AND PFIC_INFO_FOLHA_CERT_NASC = ? "
                + "AND PFIC_DATA_EXPED_CERT_NASC = ? "
                + "AND PFIC_MUNI_CODG_CERT_NASC = ? "
                + "AND PFIC_PFIS_IDEN <> ?";
        ResultSet objRS = null;
        try {
            PreparedStatement pstmt = getObjTransacao().prepararSQL(sql);
            if (pstmt == null) {
                erroMensagem = getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                return false;
            }

            if (numrCertNasc == null || numrCertNasc.equals("")) {
                pstmt.setNull(1, Types.VARCHAR);
            } else {
                pstmt.setString(1, numrCertNasc);
            }

            if (cartorioCertNasc == null || cartorioCertNasc.equals("")) {
                pstmt.setNull(2, Types.VARCHAR);
            } else {
                pstmt.setString(2, cartorioCertNasc);
            }

            if (livroCertNasc == null || livroCertNasc.equals("")) {
                pstmt.setNull(3, Types.VARCHAR);
            } else {
                pstmt.setString(3, livroCertNasc);
            }

            if (folhaCertNasc == null || folhaCertNasc.equals("")) {
                pstmt.setNull(4, Types.VARCHAR);
            } else {
                pstmt.setString(4, folhaCertNasc);
            }

            if (dataExpedCertNasc == null) {
                pstmt.setNull(5, Types.DATE);
            } else {
                pstmt.setString(5, sdf.format(dataExpedCertNasc));
            }

            if (codgMuniCertNasc == 0) {
                pstmt.setNull(6, Types.INTEGER);
            } else {
                pstmt.setInt(6, codgMuniCertNasc);
            }

            pstmt.setLong(7, idDependente);

            objRS = getObjTransacao().consultarSQL(pstmt);
            if (objRS == null || !objRS.next()) {
                erroMensagem = objRS == null ? getObjTransacao().getErroMensagem()
                        : "Certido de nascimento no encontrada.";
                erroCodigo = objRS == null ? getObjTransacao().getErroCodigo()
                        : ERRO_CONSULTA_INVALIDA;
                return false;
            } else {  /*
                 * Se encontrado
                 */
                this.numrCertNasc = numrCertNasc;
                this.cartorioCertNasc = cartorioCertNasc;
                this.livroCertNasc = livroCertNasc;
                this.folhaCertNasc = folhaCertNasc;
                if (dataExpedCertNasc == null) {
                    this.dataExpedCertNasc = null;
                } else {
                    this.dataExpedCertNasc = (java.util.Date) dataExpedCertNasc.clone();
                }
                this.codgMuniCertNasc = codgMuniCertNasc;
                return true;
            }
        } catch (SQLException e) {
            erroMensagem = e.getMessage();
            erroCodigo = e.getErrorCode();
            return false;
        } finally {
            try {
                if (objRS != null) {
                    objRS.close();
                }
            } catch (SQLException ex) {
                Logger.getRootLogger().error("Erro ao fechar ResultSet: " + ex.getMessage());
            }
        }
    }

    private void limparPropriedades() {
        this.iden = 0;
        this.tipo = null;
        this.nome = null;
        this.nomeMae = null;
        this.nomePai = null;
        this.dataNascimento = null;
        this.sexo = null;
        this.numrCPF = 0;
        this.numrRG = null;
        this.orgaoRG = null;
        this.ufRg = null;
        this.dataExpedicaoRG = null;
        this.numrMatrBase = 0;
        this.tipoSanguineo = null;
        this.tipoFatorRh = '\u0000';
        this.indicadorDoador = '\u0000';
        this.tipoEstadoCivil = '\u0000';
        this.tipoGrauInstrucao = 0;
        this.grauInstrucao = 0;
        this.codgMuniNatu = 0;
        this.codgPaisNatu = 0;
        this.paisNacionalidade = 0;
        this.anoChegadaBrasil = 0;
        this.dataNaturalizacao = null;

        this.limparIndAlteracao();

        this.limparPropriedadesComplemento();

        erroMensagem = null;
        erroCodigo = 0;
    }

    private void limparIndAlteracao() {
        this.altNumrCPF = false;
        this.altNome = false;
        this.altDataNascimento = false;
        this.altNumrRG = false;
        this.altNomeMae = false;
        this.altNomePai = false;
        this.altPaisNacionalidade = false;
        this.altDataNaturalizacao = false;
        this.altUFRG = false;
        this.altExpedicaoRG = false;
        this.altOrgaoRG = false;
        this.altNumrMatrBase = false;
        this.altSexo = false;
        this.altNumrCNH = false;
        this.altTipoCategCNH = false;
        this.altDataExpedCNH = false;
        this.altDataValidCNH = false;
        this.altNumrTitEleitor = false;
        this.altZonaEleitor = false;
        this.altSecaoEleitor = false;
        this.altDataExpedTitEleitor = false;
        this.altStatServMilit = false;
        this.altNumrCertMilitar = false;
        this.altSiglForcaMilitar = false;
        this.altCodgRegiaoMilitar = false;
        this.altMatriculaCertObito = false;
        this.altNumrCertObito = false;
        this.altCartorioCertObito = false;
        this.altLivroCertObito = false;
        this.altFolhaCertObito = false;
        this.altDataExpedCertObito = false;
        this.altCodgMunicObito = false;
        this.altDescCausaMortis = false;
        this.altTipoEstadoCivil = false;
        this.altTipoGrauInstrucao = false;
        this.altGrauInstrucao = false;
        this.altCodgPaisNatu = false;
        this.altAnoChegadaBrasil = false;
        this.altCodgMuniNatu = false;
        this.altMatriculaCertNasc = false;
        this.altMatriculaCertCasamento = false;
    }

    private void limparPropriedadesComplemento() {
        leuComplemento = false;
        this.dataObito = null;

        this.altDataObito = false;
    }

    public final boolean migraPreparar() {
        String sqlMigra =
                "INSERT INTO "
                + "PESSOAS_FISICAS (PFIS_IDEN, "
                + "PFIS_NOME, PFIS_NOME_MAE, PFIS_NOME_PAI, "
                + "PFIS_DATA_NASC, PFIS_SIGL_SEXO, PFIS_NUMR_CPF, "
                + "PFIS_NUMR_RG, PFIS_DATA_EXPED_RG, "
                + "PFIS_SIGL_ORGAO_RG, PFIS_UFED_SIGL_RG, "
                + "PFIS_NUMR_MATRICULA_BASE, PFIS_TIPO_SANGUINEO, "
                + "PFIS_TIPO_FATOR_RH, PFIS_INDI_DOADOR, "
                + "PFIS_PAIS_CODG_NACIONALIDADE, PFIS_TIPO_ESTADO_CIVIL, "
                + "PFIS_TIPO_GRAU_INSTRUCAO, PFIS_GINS_CODG, "
                + "PFIS_MUNI_CODG_NATURALIDADE, PFIS_PAIS_CODG_NASCIMENTO, "
                + "PFIS_NUMR_ANO_CHEGADA_BRASIL, PFIS_DATA_NATURALIZACAO"
                + ") VALUES ("
                + "?, ?, ?, ?, TO_DATE(?,'DDMMYYYY'), ?, ?, ?, TO_DATE(?,'DDMMYYYY'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?,'DDMMYYYY')"
                + ")";

        pstmtMigracao = getObjTransacao().prepararSQL(sqlMigra);

        return (pstmtMigracao == null);
    }

    public final boolean migraIncluir() {
        long prox = this.proximoID();
        if (prox == 0) { // erro de obteno de prximo
            erroMensagem = "Erro ao obter prximo:" + erroMensagem;
            erroCodigo = getObjTransacao().getErroCodigo();
            Logger.getRootLogger().error(erroCodigo + " - " + erroMensagem);
            return false;
        }

        try {
            int i = 1;
            pstmtMigracao.setLong(i++, prox);
            pstmtMigracao.setString(i++, this.nome);
            pstmtMigracao.setString(i++, this.nomeMae);
            pstmtMigracao.setString(i++, this.nomePai);
            if (this.dataNascimento == null) {
                pstmtMigracao.setNull(i++, Types.DATE);
            } else {
                pstmtMigracao.setString(i++, sdf.format(this.dataNascimento));
            }
            if (this.sexo == null) {
                pstmtMigracao.setNull(i++, Types.CHAR);
            } else {
                pstmtMigracao.setString(i++, this.sexo);
            }
            if (this.numrCPF == 0) {
                pstmtMigracao.setNull(i++, Types.BIGINT);
            } else {
                pstmtMigracao.setLong(i++, this.numrCPF);
            }
            pstmtMigracao.setString(i++, this.numrRG);
            if (this.dataExpedicaoRG == null) {
                pstmtMigracao.setNull(i++, Types.DATE);
            } else {
                pstmtMigracao.setString(i++, sdf.format(this.dataExpedicaoRG));
            }
            pstmtMigracao.setString(i++, this.orgaoRG);
            pstmtMigracao.setString(i++, this.ufRg);
            if (this.numrMatrBase == 0) {
                pstmtMigracao.setNull(i++, Types.BIGINT);
            } else {
                pstmtMigracao.setLong(i++, this.numrMatrBase);
            }
            pstmtMigracao.setString(i++, this.tipoSanguineo);
            if (this.tipoFatorRh == '\u0000') {
                pstmtMigracao.setNull(i++, Types.CHAR);
            } else {
                pstmtMigracao.setString(i++, String.valueOf(this.tipoFatorRh));
            }
            if (this.indicadorDoador == '\u0000') {
                pstmtMigracao.setNull(i++, Types.CHAR);
            } else {
                pstmtMigracao.setString(i++, String.valueOf(this.indicadorDoador));
            }
            if (this.paisNacionalidade == 0) {
                pstmtMigracao.setNull(i++, Types.INTEGER);
            } else {
                pstmtMigracao.setInt(i++, this.paisNacionalidade);
            }
            if (this.tipoEstadoCivil == '\u0000') {
                pstmtMigracao.setNull(i++, Types.CHAR);
            } else {
                pstmtMigracao.setString(i++, this.deparaEstadoCivil(this.tipoEstadoCivil));
            }
            if (this.tipoGrauInstrucao == 0) {
                pstmtMigracao.setNull(i++, Types.INTEGER);
            } else {
                pstmtMigracao.setInt(i++, this.tipoGrauInstrucao);
            }
            if (this.grauInstrucao == 0) {
                pstmtMigracao.setNull(i++, Types.INTEGER);
            } else {
                pstmtMigracao.setInt(i++, this.deparaGrauInstrucao(this.grauInstrucao));
            }
            if (this.codgMuniNatu == 0) {
                pstmtMigracao.setNull(i++, Types.INTEGER);
            } else {
                pstmtMigracao.setInt(i++, this.codgMuniNatu);
            }
            if (this.codgPaisNatu == 0) {
                pstmtMigracao.setNull(i++, Types.INTEGER);
            } else {
                pstmtMigracao.setInt(i++, this.deparaPaisNascimento(this.codgPaisNatu));
            }
            if (this.anoChegadaBrasil == 0) {
                pstmtMigracao.setNull(i++, Types.INTEGER);
            } else {
                pstmtMigracao.setInt(i++, this.anoChegadaBrasil);
            }
            if (this.dataNaturalizacao == null) {
                pstmtMigracao.setNull(i++, Types.DATE);
            } else {
                pstmtMigracao.setString(i++, sdf.format(this.dataNaturalizacao));
            }

            if (getObjTransacao().executarSQL(pstmtMigracao, false) == 0) {
                erroMensagem = "iden:" + prox + ":" + getObjTransacao().getErroMensagem();
                erroCodigo = getObjTransacao().getErroCodigo();
                Logger.getRootLogger().error("------------------------------------------------------------------------------");
                Logger.getRootLogger().error("Erro na Incluso: " + this.numrCPF + " - " + this.nome);
                Logger.getRootLogger().error(erroCodigo + " - " + erroMensagem);
                Logger.getRootLogger().error("------------------------------------------------------------------------------");
                return false;
            }

            this.iden = prox;

            if (!this.incluirFonetica(FON_NOME, this.nome)) {
                this.iden = 0;
                return false;
            }

            if (!this.incluirFonetica(FON_MAE, this.nomeMae)) {
                this.iden = 0;
                return false;
            }

            Logger.getRootLogger().info("Inluido com sucesso: " + this.numrCPF + " - " + this.nome);
            return true;

        } catch (SQLException e) {
            erroMensagem = e.getMessage() + " - " + getObjTransacao().getErroMensagem();
            erroCodigo = getObjTransacao().getErroCodigo();
            Logger.getRootLogger().error(erroCodigo + " - " + erroMensagem);
            return false;
        }
    }

    public final void migraFecharPSTMT() {
        try {
            pstmtMigracao.close();
        } catch (Exception e) {
            Logger.getRootLogger().error(e);
        }
    }

    private int deparaPaisNascimento(int codgPaisNasc) {
        int codigo = 0;
        switch (codgPaisNasc) {
            case 1:
            case 9:
            case 10:
                codigo = 1;
                break;
            case 2:
                codigo = 62;
                break;
            case 3:
                codigo = 71;
                break;
            case 4:
                codigo = 80;
                break;
            case 5:
                codigo = 73;
                break;
            case 6:
                codigo = 224;
                break;
            case 7:
                codigo = 52;
                break;
            case 8:
                codigo = 88;
                break;
            default:
                break;
        }
        return codigo;
    }

    private int deparaGrauInstrucao(int codgGrauInst) {
        int codigo = 0;
        switch (codgGrauInst) {
            case 1:
            case 9:
            case 10:
                codigo = 1;
                break;
            case 2:
                codigo = 62;
                break;
            case 3:
                codigo = 71;
                break;
            case 4:
                codigo = 80;
                break;
            case 5:
                codigo = 73;
                break;
            case 6:
                codigo = 224;
                break;
            case 7:
                codigo = 52;
                break;
            case 8:
                codigo = 88;
                break;
            default:
                break;
        }
        return codigo;
    }

    private String deparaEstadoCivil(char estadoCivil) {
        char retorno = '\u0000';
        switch (estadoCivil) {
            case '1':
                retorno = 'S';
                break;
            case '2':
                retorno = 'C';
                break;
            case '3':
                retorno = 'D';
                break;
            case '4':
                retorno = 'Q';
                break;
            case '5':
                retorno = 'V';
                break;
            case '7':
                retorno = 'J';
                break;
            default:
                break;
        }
        return retorno == '\u0000' ? null : String.valueOf(retorno);
    }    
  

    public final String getMatriculaCertObito() {
        return matriculaCertObito;
    }

    public final void setMatriculaCertObito(String matriculaCertObito) {
        if (!(emInclusao || emAlteracao)) {
            this.matriculaCertObito = matriculaCertObito;
            return;
        }
        this.altMatriculaCertObito = true;
        this.matriculaCertObito = matriculaCertObito;
    }

    public final String getMatriculaCertNasc() {
        return matriculaCertNasc;
    }

    public final void setMatriculaCertNasc(String matriculaCertNasc) {
        if (!(emInclusao || emAlteracao)) {
            this.matriculaCertNasc = matriculaCertNasc;
            return;
        }
        this.altMatriculaCertNasc = true;
        this.matriculaCertNasc = matriculaCertNasc;
    }

    public final String getMatriculaCertCasamento() {
        return matriculaCertCasamento;
    }

    public final void setMatriculaCertCasamento(String matriculaCertCasamento) {
        if (!(emInclusao || emAlteracao)) {
            this.matriculaCertCasamento = matriculaCertCasamento;
            return;
        }
        this.altMatriculaCertCasamento = true;
        this.matriculaCertCasamento = matriculaCertCasamento;
    }

    public Transacao getObjTransacao() {
        return objTransacao;
    }

    public void setObjTransacao(Transacao objTransacao) {
        this.objTransacao = objTransacao;
    }    
    
    
	/**
	 ** Mtodo de que consulta a tabela Dominios, onde vai me trazer a Sigla e Descrio do estado civil. * *   
	 */
    private ResultSet getEstadoCivil() {
    /**
     * * Listando todos Estados Civis ordenando o campo significado. * *        
    */    	
    	sql = "SELECT VALOR_INICIAL SIGLACIVIL, SIGNIFICADO DESCRICAO FROM DOMINIOS WHERE NOME = 'ESTADO CIVIL' ORDER BY SIGNIFICADO";      	
    	        
        //Prepara SQL
        PreparedStatement pstmt = objTransacao.prepararSQL(sql);
        if (pstmt == null) {
            erroCodigo = objTransacao.getErroCodigo();
            erroMensagem = objTransacao.getErroMensagem();
            return null;
        }

        ResultSet rset = objTransacao.consultarSQL(pstmt);
        if (rset == null) {
            erroMensagem = objTransacao.getErroMensagem();
            erroCodigo = objTransacao.getErroCodigo();
            return null;
        }
        return rset;
    }    
    
    /**
     * Retorna todos os estados civis, do mtodo getEstadoCivil()
     *
     * @return Um ResultSet contendo as seguintes colunas: SIGLACIVIL - Sigla do estado civil; DESCRICAO - Descrio do estado civil.
     */
   public ResultSet getEstadoCivilNome() {
        return this.getEstadoCivil();
    } 

}
