				</div>
			</div>

		</div> <!-- /container -->
</body>

<script>
//function addCommas( sValue ) 
//{
//   if (sValue.length > 0 ) {
//   sValue = sValue.replace(/[^0-9\.]/g,'');
//   sValue1 = parseFloat(sValue);
//   sValue = sValue1.toString();
//       var sRegExp = new RegExp('(-?[0-9]+)([0-9]{3})');
//
//       while(sRegExp.test(sValue)) {
//           sValue = sValue.replace(sRegExp, '$1.$2');
//       }
//   }
//   return "Rp. " + sValue;
//}

function addCommas (num) {
    return "Rp. " + num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.")
}

$( document ).ready(function() {


   $('.numberfilter').each(function() {
       $(this).text(addCommas($(this).text()));
   });
});
</script>

</html>
