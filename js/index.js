$(function(){
   $("#modal-mensagem").modal();  
});

$("#btnConnection").click(function(){

	var connectionName  = $("#connection-name").val();

	console.log(connectionName);
	treeArray = [{
    text: connectionName,
    nodes:[
      {
      text:"Management_Database",
        nodes:[
          {
          	text:"Employees",
          },
          {
            text:"Departmants",
          }
        ]
      },
      {
      text:"banco1",
        nodes:[
          {
          text:"tabela 1"
          },
          {
            text:"tabela 2"
          }
        ]
      },
    ]
    }];

   	createTree(treeArray);
});