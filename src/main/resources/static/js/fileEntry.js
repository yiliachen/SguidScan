var getUrlParameter = function getUrlParameter(sParam) {
		    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
		        sURLVariables = sPageURL.split('&'),
		        sParameterName,
		        i;

		    for (i = 0; i < sURLVariables.length; i++) {
		        sParameterName = sURLVariables[i].split('=');

		        if (sParameterName[0] === sParam) {
		            return sParameterName[1] === undefined ? true : sParameterName[1];
		        }
		    }
		};
		$(document).ready(
		function() {
			//?&filename="+fileNameParam,
			var fileNameParam = getUrlParameter("filename");
			var table = $('#FileEntryTable').DataTable({
				"sAjaxSource" : "/fileentry",
				"sAjaxDataProp" : "",
				"order" : [ [ 0, "asc" ] ],
				"aoColumns" : [ {
					"mData" : "filename",
					"render": function(data, type, full, meta){
						var path=data.replace("LATEST/", "");
						path=path.replace(".rdd", "");
						path=path.substr(path.indexOf("FUSION"));
						return '<a target="_blank" href="https://codesearch.oraclecorp.com/cs/xref/'+path+'">'+path+'</a>'
					}
				}, {
					"mData" : "entryName"
				}, {
					"mData" : "type"
				}, {
					"mData" : "occourance"
				} ],
				dom : 'lBfrtip',
				buttons : [ {
					extend : 'collection',
					text : 'Export',
					buttons : [ 'copy', 'excel', 'csv', 'pdf', 'print' ]
				} ]
			})
			// Setup - add a text input to each footer cell
			$('#FileEntryTable tfoot th').each(
					function() {
						var title = $(this).text();
						$(this).html(
								'<input type="text" placeholder="Search '
										+ title + '" />');
					});

			// Apply the search
			table.columns().every(function() {
				var that = this;

				$('input', this.footer()).on('keyup change', function() {
					if (that.search() !== this.value) {
						that.search(this.value).draw();
					}
				});
			});
		});
