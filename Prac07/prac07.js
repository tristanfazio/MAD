$(window).on("load",function()
{
  $("#plusButton").on("click",plusFunction);
  $("#minusButton").on("click",minusFunction);
  $("#divideButton").on("click",divideFunction);
  $("#multiplyButton").on("click",multiplyFunction);
});

function plusFunction()
{
  console.log("Plus Function");
  let result = parseInt($("#num1").val()) + parseInt($("#num2").val());
  $("#result").val(result);
}
function minusFunction()
{
  console.log("Minus Function");
  let result = parseInt($("#num1").val()) - parseInt($("#num2").val());
  $("#result").val(result);
}
function divideFunction()
{
  console.log("Divide Function");
  let result = parseInt($("#num1").val()) / parseInt($("#num2").val());
  $("#result").val(result);
}
function multiplyFunction()
{
  console.log("Multiply Function");
  let result = parseInt($("#num1").val()) * parseInt($("#num2").val());
  $("#result").val(result);
}
