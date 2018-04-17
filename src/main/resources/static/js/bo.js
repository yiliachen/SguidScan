$(document)
		.ready(
				function() {
					var $table = $('#ProcessStatus')
							.DataTable(
									{
										"lengthMenu" : [ 100 ],
										"aoColumns" : [ {
											"mData" : "vo_def"
										}, {
											"mData" : "vo_name"
										}, {
											"mData" : "vo_extends"
										}, {
											"mData" : "eo_name"
										}, {
											"mData" : "dbo_name"
										}, {
											"mData" : "dbo_col_name"
										}, {
											"mData" : "sdf_path"
										}, {
											"mData" : "sdf_am"
										}, {
											"mData" : "sdf_vo"
										} ],
										dom : 'lBfrtip',
										buttons : [ {
											extend : 'collection',
											text : 'Export',
											buttons : [ 'copy', 'excel', 'csv',
													'pdf', 'print' ]
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

					$('#ProcessStatus tfoot th').each(
							function() {
								var title = $(this).text();
								$(this).html(
										'<input type="text" placeholder="Search '
												+ title + '" />');
							});

					// Apply the search
					$table.columns().every(
							function() {
								var that = this;

								$('input', this.footer()).on('keyup change',
										function() {
											if (that.search() !== this.value) {
												that.search(this.value).draw();
											}
										});
							});

					var $form = $('#searchForm');
					var $button = $('#submitBtn');
					$button.on('click', function(e) {
						e.preventDefault();
						$form.ajaxSubmit({
							url : $form.attr('action'),
							type : 'post',
							dataType : 'json',
							success : function(result) {
								$table.clear().draw();
								$table.rows.add(result.content).draw();
							}
						})
					});
				})
