console.log("sidebars");

(function () {
  var collapseElementList = [].slice.call(
    document.querySelectorAll(".collapse")
  );
  console.log(collapseElementList.length);
  // var collapseList = collapseElementList.map(function (collapseEl) {
  //   return new window.bootstrap.Collapse(collapseEl);
  // });
})();
