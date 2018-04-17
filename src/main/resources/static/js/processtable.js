$(document)
		.ready(
				function() {
					var ajxpath = "/fileentrysum?startPath=";
					if(typeof(startPath) != 'undefined' && startPath != null && startPath.length > 0){
						ajxpath = ajxpath+startPath;
					}else{
						ajxpath = "/fileentrysumall";
						startPath = '';	
					}
					var nullsguidajxpath = "/fileentrysum/object?startPath="+startPath+"&nullSguid=Y&dupSguid=&deSguid=&noSguid=&sguidPopulated=N";
			        var dupsguidajxpath = "/fileentrysum/object?startPath="+startPath+"&nullSguid=&dupSguid=Y&deSguid=&noSguid=&sguidPopulated=Y";
			        var nosguidajxpath = "/fileentrysum/object?startPath="+startPath+"&nullSguid=&dupSguid=&deSguid=&noSguid=Y&sguidPopulated=N";
			        var popedsguidajxpath = "/fileentrysum/object?startPath="+startPath+"&nullSguid=&dupSguid=&deSguid=&noSguid=&sguidPopulated=Y";
			        var desguidajxpath = "/fileentrysum/object?startPath="+startPath+"&nullSguid=&dupSguid=&deSguid=Y&noSguid=&sguidPopulated=Y";
					
					var table = $('#ProcessStatus')
							.DataTable(
									{
										"sAjaxSource" : ajxpath,
										"sAjaxDataProp" : "",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : "filename",
													"render" : function(data,
															type, full, meta) {
														return '<a href="FileEntrys?filename='
																+ data
																+ '">'
																+ data + '</a>'
													}
												}, {
													"mData" : "sguidIsNull"
												}, {
													"mData" : "noSguidTag"
												}, {
													"mData" : "sguidPopulated"
												}, {
													"mData" : "sguidDuplicated"
												}
										/*
										 * ,{ "mData" : "hasRowkey" }, { "mData" :
										 * "derecSguidDuplicated" }
										 */],
										dom : 'lBfrtip',
										buttons : [ {
											extend : 'collection',
											text : 'Export',
											buttons : [ 'copy', 'excel', 'csv']
										} ],
										initComplete : function() {
											this
													.api()
													.columns()
													.every(
															function() {
																var column = this;
																var select = $(
																		'<select><option value=""></option></select>')
																		.appendTo(
																				$(
																						column
																							.footer())
																						.empty())
																		.on(
																				'change',
																				function() {
																					var val = $.fn.dataTable.util
																							.escapeRegex($(
																									this)
																									.val());

																					column
																							.search(
																									val ? '^'
																											+ val
																											+ '$'
																											: '',
																									true,
																									false)
																							.draw();
																				});

																column
																		.data()
																		.unique()
																		.sort()
																		.each(
																				function(
																						d,
																						j) {
																					select
																							.append('<option value="'
																									+ d
																									+ '">'
																									+ d
																									+ '</option>')
																				});
															});
										}
									});

					// // Setup - add a text input to each footer cell
					$('#ProcessStatus tfoot th').each(
							function() {
								var title = $(this).text();
								$(this).html(
										'<input type="text" placeholder="Search '
												+ title + '" />');
							});

					// Apply the search
					table.columns().every(
							function() {
								var that = this;

								$('input', this.footer()).on('keyup change',
										function() {
											if (that.search() !== this.value) {
												that.search(this.value).draw();
											}
										});
							});
					

					var nullSguidTab = $('#NullSguid')
							.DataTable(
									{
										"sAjaxSource" : nullsguidajxpath,
										"sAjaxDataProp" : "",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : "filename",
													"render" : function(data,
															type, full, meta) {
														return '<a href="FileEntrys?filename='
																+ data
																+ '">'
																+ data + '</a>'
													}
												}
										/*
										 * ,{ "mData" : "hasRowkey" }, { "mData" :
										 * "derecSguidDuplicated" }
										 */],
										dom : 'lBfrtip',
										buttons : [ {
											extend : 'collection',
											text : 'Export',
											buttons : [ 'copy', 'excel', 'csv' ]
										} ],
										initComplete : function() {
											this
													.api()
													.columns()
													.every(
															function() {
																var column = this;
																var select = $(
																		'<select><option value=""></option></select>')
																		.appendTo(
																				$(
																						column
																								.footer())
																						.empty())
																		.on(
																				'change',
																				function() {
																					var val = $.fn.dataTable.util
																							.escapeRegex($(
																									this)
																									.val());

																					column
																							.search(
																									val ? '^'
																											+ val
																											+ '$'
																											: '',
																									true,
																									false)
																							.draw();
																				});

																column
																		.data()
																		.unique()
																		.sort()
																		.each(
																				function(
																						d,
																						j) {
																					select
																							.append('<option value="'
																									+ d
																									+ '">'
																									+ d
																									+ '</option>')
																				});
															});
										}
									});

					// // Setup - add a text input to each footer cell
					$('#NullSguid tfoot th').each(
							function() {
								var title = $(this).text();
								$(this).html(
										'<input type="text" placeholder="Search '
												+ title + '" />');
							});

					// Apply the search
					nullSguidTab.columns().every(
							function() {
								var that = this;

								$('input', this.footer()).on('keyup change',
										function() {
											if (that.search() !== this.value) {
												that.search(this.value).draw();
											}
										});
							});
					

					var dupSguidTab = $('#DupSguid')
							.DataTable(
									{
										"sAjaxSource" : dupsguidajxpath,
										"sAjaxDataProp" : "",
										"order" : [ [ 0, "asc" ] ],
										"aoColumns" : [
												{
													"mData" : "filename",
													"render" : function(data,
															type, full, meta) {
														return '<a href="FileEntrys?filename='
																+ data
																+ '">'
																+ data + '</a>'
													}
												}
										/*
										 * ,{ "mData" : "hasRowkey" }, { "mData" :
										 * "derecSguidDuplicated" }
										 */],
										dom : 'lBfrtip',
										buttons : [ {
											extend : 'collection',
											text : 'Export',
											buttons : [ 'copy', 'excel', 'csv']
										} ],
										initComplete : function() {
											this
													.api()
													.columns()
													.every(
															function() {
																var column = this;
																var select = $(
																		'<select><option value=""></option></select>')
																		.appendTo(
																				$(
																						column
																								.footer())
																						.empty())
																		.on(
																				'change',
																				function() {
																					var val = $.fn.dataTable.util
																							.escapeRegex($(
																									this)
																									.val());

																					column
																							.search(
																									val ? '^'
																											+ val
																											+ '$'
																											: '',
																									true,
																									false)
																							.draw();
																				});

																column
																		.data()
																		.unique()
																		.sort()
																		.each(
																				function(
																						d,
																						j) {
																					select
																							.append('<option value="'
																									+ d
																									+ '">'
																									+ d
																									+ '</option>')
																				});
															});
										}
									});

					//			// Setup - add a text input to each footer cell
					$('#DupSguid tfoot th').each(
							function() {
								var title = $(this).text();
								$(this).html(
										'<input type="text" placeholder="Search '
												+ title + '" />');
							});

					// Apply the search
					dupSguidTab.columns().every(
							function() {
								var that = this;

								$('input', this.footer()).on('keyup change',
										function() {
											if (that.search() !== this.value) {
												that.search(this.value).draw();
											}
										});
							});
					
					
					var noSguidTab = $('#NoSguid')
					.DataTable(
							{
								"sAjaxSource" : nosguidajxpath,
								"sAjaxDataProp" : "",
								"order" : [ [ 0, "asc" ] ],
								"aoColumns" : [
										{
											"mData" : "filename",
											"render" : function(data,
													type, full, meta) {
												return '<a href="FileEntrys?filename='
														+ data
														+ '">'
														+ data + '</a>'
											}
										}
								/*
								 * ,{ "mData" : "hasRowkey" }, { "mData" :
								 * "derecSguidDuplicated" }
								 */],
								dom : 'lBfrtip',
								buttons : [ {
									extend : 'collection',
									text : 'Export',
									buttons : [ 'copy', 'excel', 'csv']
								} ],
								initComplete : function() {
									this
											.api()
											.columns()
											.every(
													function() {
														var column = this;
														var select = $(
																'<select><option value=""></option></select>')
																.appendTo(
																		$(
																				column
																						.footer())
																				.empty())
																.on(
																		'change',
																		function() {
																			var val = $.fn.dataTable.util
																					.escapeRegex($(
																							this)
																							.val());

																			column
																					.search(
																							val ? '^'
																									+ val
																									+ '$'
																									: '',
																							true,
																							false)
																					.draw();
																		});

														column
																.data()
																.unique()
																.sort()
																.each(
																		function(
																				d,
																				j) {
																			select
																					.append('<option value="'
																							+ d
																							+ '">'
																							+ d
																							+ '</option>')
																		});
													});
								}
							});

			//			// Setup - add a text input to each footer cell
			$('#NoSguid tfoot th').each(
					function() {
						var title = $(this).text();
						$(this).html(
								'<input type="text" placeholder="Search '
										+ title + '" />');
					});

			// Apply the search
			noSguidTab.columns().every(
					function() {
						var that = this;

						$('input', this.footer()).on('keyup change',
								function() {
									if (that.search() !== this.value) {
										that.search(this.value).draw();
									}
								});
					});
			
				});
