
var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope) {
  /*
  message = "menssagem de erro ao retornar query"
  */
  $scope.values = [];
  $scope.attributs = [];
  $scope.script


  //investigar dois valores iguais no mesmo array ocorre um problema
  function dataEmployes(){
    $scope.values = [
      ["1","Igor","1000","2"],
      ["2","Pedro","1500","1"],
      ["3","Carlos","1500","2"]
    ];
    $scope.attributs = ["id","name", "salary" ,"department_id"];
  }

  function dataDepartments(){
    $scope.values = [
      ["1","sales", "2"],
      ["2","technology","3"]
    ];
    $scope.attributs = ["id","name", "boss_id"];
  }

  $(function(){
     $("#modal-mensagem").modal();  
  });


  $scope.tes =function(){
    console.log("TESTANDO");
    if($scope.script.toLowerCase() == "select * from employees"){
      dataEmployes();
    }else if($scope.script.toLowerCase() == "select * from departments"){
      dataDepartments();
    }
    
  }  

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
              text:"Departments",
            }
          ]
        }
      ]
      }];

      createTree(treeArray);
  });
});