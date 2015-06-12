import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SegundaActivity extends Activity {
    
	Button btnSalvar, btnCancelar, btnNovo;
	EditText txtNome, txtEndereco, txtTelefone;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CarregarInterfaceListagem();
    }
    
    public void CarregarInterfaceListagem()
    {
    	setContentView(R.layout.main);
        
        //configurando o botÃ£o de criar novo cadastro
        btnNovo = (Button)findViewById(R.id.btnNovo);
        btnNovo.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				CarregarInterfaceCadastro();
			}});
        
        CarregarLista(this);
    }
    
    public void CarregarInterfaceCadastro()
    {
    	setContentView(R.layout.cadastro);
    	
    	//configurando o botÃ£o de cancelar cadastro
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				CarregarInterfaceListagem();
			}});
        
        //configurando o formulÃ¡rio de cadastro
        txtNome = (EditText)findViewById(R.id.txtNome);
        txtEndereco = (EditText)findViewById(R.id.txtEndereco);
        txtTelefone = (EditText)findViewById(R.id.txtTelefone);
        
        //configurando o botÃ£o de salvar
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				SalvarCadastro();
			}});
    }
    
    public void SalvarCadastro()
    {
    	ContextoDados db = new ContextoDados(this);
		db.InserirContato(txtNome.getText().toString(), txtTelefone.getText().toString(), txtEndereco.getText().toString());
		setContentView(R.layout.main);
		CarregarLista(this);
    }
    
    public void CarregarLista(Context c)
    {
    	ContextoDados db = new ContextoDados(c);
        ContatosCursor cursor = db.RetornarContatos(ContatosCursor.OrdenarPor.NomeCrescente);
        
        for( int i=0; i <cursor.getCount(); i++)
        {
        	cursor.moveToPosition(i);
        	ImprimirLinha(cursor.getNome(), cursor.getTelefone(), cursor.getEndereco());
        }
    }
    
    public void ImprimirLinha(String nome, String telefone, String endereco)
    {
    	TextView tv = (TextView)findViewById(R.id.listaContatos);
    	
    	if(tv.getText().toString().equalsIgnoreCase("Nenhum contato cadastrado."))
    		tv.setText("");
    	
    	tv.setText(tv.getText() + "\r\n" +"Nome: "+ nome + "\n " +"Telefone: "+ telefone + "\n"+ "Endereço: "+endereco);
    }
}
